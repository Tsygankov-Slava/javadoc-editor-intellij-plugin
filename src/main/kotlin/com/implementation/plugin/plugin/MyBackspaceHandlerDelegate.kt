package com.implementation.plugin.plugin

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.project.Project

class BackspaceActionHandler(private val originalHandler: EditorActionHandler, private val project: Project, private var mainDocument: Document): EditorActionHandler() {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        println("RUNNING")
        val runnable = Runnable {
            mainDocument.setReadOnly(false)
            mainDocument.insertString(0, "aa")
        }
        WriteCommandAction.runWriteCommandAction(project, runnable)
        //originalHandler.execute(editor, caret, dataContext)
    }
}