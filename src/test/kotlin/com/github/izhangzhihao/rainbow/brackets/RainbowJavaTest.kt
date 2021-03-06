package com.github.izhangzhihao.rainbow.brackets

import com.github.izhangzhihao.rainbow.brackets.settings.RainbowSettings
import com.intellij.ide.highlighter.JavaFileType
import com.intellij.psi.PsiDocumentManager
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase
import io.kotlintest.matchers.shouldBe

class RainbowJavaTest : LightCodeInsightFixtureTestCase() {
    override fun tearDown() {
        super.tearDown()
        val rainbowSettings = RainbowSettings.instance
        rainbowSettings.isRainbowEnabled = true
        rainbowSettings.isEnableRainbowAngleBrackets = true
        rainbowSettings.isEnableRainbowRoundBrackets = true
        rainbowSettings.isEnableRainbowSquareBrackets = true
        rainbowSettings.isEnableRainbowSquigglyBrackets = true
        rainbowSettings.isDoNOTRainbowifyBracketsWithoutContent = false
    }

    fun testRainbowForJava() {
        val code =
                """
public class Test<T> {
    public Boolean test() {
        return "true".equals("false") || "false".equals("true".concat("false".length() + "*"));
    }
}
                """.trimIndent()
        myFixture.configureByText(JavaFileType.INSTANCE, code)
        PsiDocumentManager.getInstance(project).commitAllDocuments()
        val doHighlighting = myFixture.doHighlighting()
        assertFalse(doHighlighting.isEmpty())
        doHighlighting.filter { brackets.contains(it.text.toChar()) }
                .map { it.forcedTextAttributes.foregroundColor }
                .toTypedArray()
                .shouldBe(
                        arrayOf(
                                angleLevel(0),
                                angleLevel(0),
                                squigglyLevel(0),
                                roundLevel(0),
                                roundLevel(0),
                                squigglyLevel(1),
                                roundLevel(0),
                                roundLevel(0),
                                roundLevel(0),
                                roundLevel(1),
                                roundLevel(2),
                                roundLevel(2),
                                roundLevel(1),
                                roundLevel(0),
                                squigglyLevel(1),
                                squigglyLevel(0)
                        )
                )
    }

    fun testDisableRainbowForJava() {
        val rainbowSettings = RainbowSettings.instance
        rainbowSettings.isRainbowEnabled = false
        val code =
                """
public class Test<T> {
    public Boolean test() {
        return "true".equals("false") || "false".equals("true".concat("false".length() + "*"));
    }
}
                """.trimIndent()
        myFixture.configureByText(JavaFileType.INSTANCE, code)
        PsiDocumentManager.getInstance(project).commitAllDocuments()
        val doHighlighting = myFixture.doHighlighting()
        assertFalse(doHighlighting.isEmpty())
        doHighlighting.filter { brackets.contains(it.text.toChar()) }
                .map { it.forcedTextAttributes.foregroundColor }
                .toTypedArray()
                .shouldBe(
                        arrayOf()
                )
    }

    fun testDisableRainbowAngleBracketsForJava() {
        val rainbowSettings = RainbowSettings.instance
        rainbowSettings.isEnableRainbowAngleBrackets = false
        val code =
                """
public class Test<T> {
    public Boolean test() {
        return "true".equals("false") || "false".equals("true".concat("false".length() + "*"));
    }
}
                """.trimIndent()
        myFixture.configureByText(JavaFileType.INSTANCE, code)
        PsiDocumentManager.getInstance(project).commitAllDocuments()
        val doHighlighting = myFixture.doHighlighting()
        assertFalse(doHighlighting.isEmpty())
        doHighlighting.filter { brackets.contains(it.text.toChar()) }
                .map { it.forcedTextAttributes.foregroundColor }
                .toTypedArray()
                .shouldBe(
                        arrayOf(
                                squigglyLevel(0),
                                roundLevel(0),
                                roundLevel(0),
                                squigglyLevel(1),
                                roundLevel(0),
                                roundLevel(0),
                                roundLevel(0),
                                roundLevel(1),
                                roundLevel(2),
                                roundLevel(2),
                                roundLevel(1),
                                roundLevel(0),
                                squigglyLevel(1),
                                squigglyLevel(0)
                        )
                )
    }

    fun testDisableRainbowRoundBracketsForJava() {
        val rainbowSettings = RainbowSettings.instance
        rainbowSettings.isEnableRainbowRoundBrackets = false
        val code =
                """
public class Test<T> {
    public Boolean test() {
        return "true".equals("false") || "false".equals("true".concat("false".length() + "*"));
    }
}
                """.trimIndent()
        myFixture.configureByText(JavaFileType.INSTANCE, code)
        PsiDocumentManager.getInstance(project).commitAllDocuments()
        val doHighlighting = myFixture.doHighlighting()
        assertFalse(doHighlighting.isEmpty())
        doHighlighting.filter { brackets.contains(it.text.toChar()) }
                .map { it.forcedTextAttributes.foregroundColor }
                .toTypedArray()
                .shouldBe(
                        arrayOf(
                                angleLevel(0),
                                angleLevel(0),
                                squigglyLevel(0),
                                squigglyLevel(1),
                                squigglyLevel(1),
                                squigglyLevel(0)
                        )
                )
    }

    fun testDisableRainbowSquigglyBracketsForJava() {
        val rainbowSettings = RainbowSettings.instance
        rainbowSettings.isEnableRainbowSquigglyBrackets = false
        val code =
                """
public class Test<T> {
    public Boolean test() {
        return "true".equals("false") || "false".equals("true".concat("false".length() + "*"));
    }
}
                """.trimIndent()
        myFixture.configureByText(JavaFileType.INSTANCE, code)
        PsiDocumentManager.getInstance(project).commitAllDocuments()
        val doHighlighting = myFixture.doHighlighting()
        assertFalse(doHighlighting.isEmpty())
        doHighlighting.filter { brackets.contains(it.text.toChar()) }
                .map { it.forcedTextAttributes.foregroundColor }
                .toTypedArray()
                .shouldBe(
                        arrayOf(
                                angleLevel(0),
                                angleLevel(0),
                                roundLevel(0),
                                roundLevel(0),
                                roundLevel(0),
                                roundLevel(0),
                                roundLevel(0),
                                roundLevel(1),
                                roundLevel(2),
                                roundLevel(2),
                                roundLevel(1),
                                roundLevel(0)
                                )
                )
    }

    fun testDoNOTRainbowifyBracketsWithoutContentForJava() {
        val rainbowSettings = RainbowSettings.instance
        rainbowSettings.isDoNOTRainbowifyBracketsWithoutContent = true
        val code =
                """
public class Test<T> {
    public Boolean test() {
        return "true".equals("false") || "false".equals("true".concat("false".length() + "*"));
    }
}
                """.trimIndent()
        myFixture.configureByText(JavaFileType.INSTANCE, code)
        PsiDocumentManager.getInstance(project).commitAllDocuments()
        val doHighlighting = myFixture.doHighlighting()
        assertFalse(doHighlighting.isEmpty())
        doHighlighting.filter { brackets.contains(it.text.toChar()) }
                .map { it.forcedTextAttributes.foregroundColor }
                .toTypedArray()
                .shouldBe(
                        arrayOf(
                                angleLevel(0),
                                angleLevel(0),
                                squigglyLevel(0),
                                squigglyLevel(1),
                                roundLevel(0),
                                roundLevel(0),
                                roundLevel(0),
                                roundLevel(1),
                                roundLevel(1),
                                roundLevel(0),
                                squigglyLevel(1),
                                squigglyLevel(0)
                        )
                )
    }
}