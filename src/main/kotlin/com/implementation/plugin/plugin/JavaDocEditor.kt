package com.implementation.plugin.plugin.UI


import com.implementation.plugin.plugin.helper_classes.MyDocRenderItem
import com.implementation.plugin.plugin.JavaDocComment
import com.intellij.refactoring.suggested.startOffset
import com.intellij.ide.highlighter.ProjectFileType
import com.intellij.refactoring.suggested.endOffset
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent


class JavaDocEditor(private val project: Project, private val javaDocComment: JavaDocComment) : DialogWrapper(true) {
    private val editor: EditorEx
    private val text: String = javaDocComment.getText()
    private val html: String = javaDocComment.getHtmlText()
    private val document = EditorFactory.getInstance().createDocument(text)
    private var dri: MyDocRenderItem

    init {
        setOKButtonText("Apply")

        editor = EditorFactory.getInstance().createEditor(document, project, ProjectFileType.INSTANCE, false) as EditorEx

        dri = MyDocRenderItem(editor, TextRange(javaDocComment.getPsi().startOffset, javaDocComment.getPsi().endOffset), html)
        dri.setIconVisible(true)
        dri.toggle()

        init()
    }

    override fun createCenterPanel(): JComponent {
        return panel {
            row() {
                cell(editor.component)
            }
        }
    }

    override fun doOKAction() {
//        val updateRenderersMethod: Method = DocRenderItem::class.java.getDeclaredMethod(
//                "updateRenderers", Editor::class.java, Boolean::class.java)
//        updateRenderersMethod.setAccessible(true)
//        updateRenderersMethod.invoke(dri, editor, false)
    }
}
