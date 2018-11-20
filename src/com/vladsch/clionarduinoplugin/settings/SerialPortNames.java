package com.vladsch.clionarduinoplugin.settings;

import com.vladsch.clionarduinoplugin.util.Utils;
import com.vladsch.clionarduinoplugin.util.ui.ComboBoxAdaptable;
import com.vladsch.clionarduinoplugin.util.ui.ComboBoxAdapter;
import com.vladsch.clionarduinoplugin.util.ui.EnumLike;
import org.jetbrains.annotations.NotNull;

public class SerialPortNames implements ComboBoxAdaptable<SerialPortNames> {
    public final EnumLike parent;
    public final int intValue;
    public final @NotNull String displayName;

    public static EnumLike<SerialPortNames> createEnum(boolean filtered) {
        return new EnumLike<>(Utils.getSerialPorts(filtered), SerialPortNames::new, true);
    }

    public SerialPortNames(final EnumLike parent, final int intValue, @NotNull final String displayName) {
        this.parent = parent;
        this.intValue = intValue;
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String name() {
        return displayName;
    }

    @Override
    public int getIntValue() {
        return intValue;
    }

    @Override
    public ComboBoxAdapter<SerialPortNames> getAdapter() {
        return parent.ADAPTER;
    }

    @Override
    public SerialPortNames[] getValues() {
        return (SerialPortNames[]) parent.values;
    }
}
