package com.nvr.android.channel.utils;

import com.beust.jcommander.IStringConverter;

import java.util.HashMap;
import java.util.Map;

public class CommaSeparatedKeyValueConverter implements IStringConverter<Map<String, String>> {
    @Override
    public Map<String, String> convert(final String value) {
        Map<String, String> result = null;
        if (!Util.isTextEmpty(value)) {
            final String[] temp = value.split(",");
            result = new HashMap<String, String>(temp.length);
            for (String s : temp) {
                final String[] keyValue = s.split("=");
                if (keyValue.length == 2) {
                    result.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return result;
    }
}
