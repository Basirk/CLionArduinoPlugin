/*
        based on CLion CPP Executable/Library Project Generators
        and
        CLionArduinoPlugin new project wizard
 */

package com.vladsch.clionarduinoplugin.generators

import com.vladsch.clionarduinoplugin.Bundle
import icons.PluginIcons
import org.jetbrains.annotations.Nls
import javax.swing.Icon

class ArduinoSketchProjectGenerator : ArduinoProjectGeneratorBase(false) {

    @Nls
    override fun getName(): String {
        return Bundle.message("new-project.sketch-project.name")
    }

    override fun getDescription(): String? {
        return Bundle.message("new-project.sketch-project.description")
    }

    override fun getLogo(): Icon? {
        return PluginIcons.arduino_project
    }

    override val templateType: String
        get() = "project/sketch"

    override fun templateVars(name: String, pascalName: String, camelName: String, snakeName: String): Map<String, String> =
            mapOf()
}
