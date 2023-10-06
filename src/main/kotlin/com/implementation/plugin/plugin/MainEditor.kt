package com.implementation.plugin.plugin

import com.implementation.plugin.plugin.UI.JavaDocEditorUI
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiDocCommentBase
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiFile

class JavaDocEditor : AnAction() {
    private var project: Project? = null
    private var file: PsiFile? = null
    private var editor: Editor? = null

    override fun actionPerformed(e: AnActionEvent) {
        project = e.getRequiredData(CommonDataKeys.PROJECT)
        file = e.getData(CommonDataKeys.PSI_FILE)
        editor = e.getRequiredData(CommonDataKeys.EDITOR)

        var psiJavaDocComment = file?.findElementAt(editor!!.caretModel.offset)
        // Handling various cursor position cases
        psiJavaDocComment = if (psiJavaDocComment?.prevSibling is PsiComment) {
            psiJavaDocComment.prevSibling
        } else {
            PsiTreeUtil.getParentOfType(psiJavaDocComment, PsiComment::class.java)
        }

        val javaDocComment = JavaDocComment(psiJavaDocComment!! as PsiDocCommentBase)

        val javaDocEditorUI = JavaDocEditorUI(project!!, javaDocComment)
        javaDocEditorUI.show()
    }
}
