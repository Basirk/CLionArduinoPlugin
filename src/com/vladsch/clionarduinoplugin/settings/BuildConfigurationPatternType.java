/*
 * Copyright (c) 2016-2018 Vladimir Schneider <vladimir.schneider@gmail.com>
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.vladsch.clionarduinoplugin.settings;

import com.vladsch.clionarduinoplugin.Bundle;
import com.vladsch.plugin.util.ui.ComboBoxAdaptable;
import com.vladsch.plugin.util.ui.ComboBoxAdapter;
import com.vladsch.plugin.util.ui.ComboBoxAdapterImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BuildConfigurationPatternType implements ComboBoxAdaptable<BuildConfigurationPatternType> {
    ALL(0, Bundle.message("settings.build-configuration-pattern-type.all")),
    ANYWHERE(1, Bundle.message("settings.build-configuration-pattern-type.any")),
    PREFIX(2, Bundle.message("settings.line-settings.remove-prefix-on-paste-type.prefix")),
    SUFFIX(3, Bundle.message("settings.line-settings.remove-prefix-on-paste-type.suffix")),
    REGEX(4, Bundle.message("settings.remove-prefix-on-paste-type.regex"));

    /**
     * Convert to caret position for paste depending on where the caret is relative
     * to indent column and setting
     *
     * @param text     text which is to match to the prefix
     * @param prefixes prefix patterns, if regex then only the first entry is used an it is the match pattern
     * @return matched prefix or empty string if text does not match prefix
     */
    public String getMatched(@NotNull String text, @Nullable String[] prefixes) {
        if (prefixes == null || prefixes.length == 0) return "";

        if (this == ALL) {
            return text;
        } else if (this == ANYWHERE) {
            for (String prefix : prefixes) {
                if (text.contains(prefix)) return prefix;
            }
        } else if (this == PREFIX) {
            for (String prefix : prefixes) {
                if (text.startsWith(prefix)) return prefix;
            }
        } else if (this == SUFFIX) {
            for (String prefix : prefixes) {
                if (text.endsWith(prefix)) return prefix;
            }
        }
        if (this == REGEX) {
            try {
                Pattern pattern;
                Matcher matcher;

                pattern = Pattern.compile(prefixes[0]);
                matcher = pattern.matcher(text);
                if (matcher.find() && matcher.start() == 0) return matcher.group();

                //pattern = Pattern.compile(prefix2);
                //matcher = pattern.matcher(text);
                //if (matcher.find() && matcher.start() == 0) return matcher.group();
            } catch (Throwable ignored) {

            }
        }
        return "";
    }

    public final int intValue;
    public final @NotNull String displayName;

    BuildConfigurationPatternType(int intValue, @NotNull String displayName) {
        this.intValue = intValue;
        this.displayName = displayName;
    }

    public static Static<BuildConfigurationPatternType> ADAPTER = new Static<>(new ComboBoxAdapterImpl<>(SUFFIX));

    @NotNull
    @Override
    public ComboBoxAdapter<BuildConfigurationPatternType> getAdapter() {
        return ADAPTER;
    }

    @Override
    public int getIntValue() { return intValue; }

    @NotNull
    public String getDisplayName() { return displayName; }

    @NotNull
    public BuildConfigurationPatternType[] getValues() { return values(); }
}
