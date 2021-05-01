package com.example.android.myfishy.utilities;

import android.util.Pair;

public enum CharState {

    DELIMITER {
        @Override
        Pair<CharState, String> handleChar(char[] line, int pos, String context) {
            char c = line[pos];
            if (isValid(c)) {
                context += c;
                return new Pair<>(CELL_CONTENT, context);
            } else if (c == '"') {
                return new Pair<>(STRING_CHAR, context);
            } else {
                return new Pair<>(DELIMITER, context);
            }
        }
    },
    CELL_CONTENT {
        @Override
        Pair<CharState, String> handleChar(char[] line, int pos, String context) {
            char c = line[pos];
            if (c == ';') {
                return new Pair<>(DELIMITER, context);
            } else {
                context += c;
                return new Pair<>(CELL_CONTENT, context);
            }
        }
    },
    STRING_CONTENT {
        @Override
        Pair<CharState, String> handleChar(char[] line, int pos, String context) {
            char c = line[pos];
            if (c == '"') {
                return new Pair<>(STRING_CHAR, context);
            } else {
                context += c;
                return new Pair<>(STRING_CONTENT, context);
            }
        }
    },
    STRING_CHAR {
        @Override
        Pair<CharState, String> handleChar(char[] line, int pos, String context) {
            char c = line[pos];
            //if (c == ';' || (c == '"' && line[pos - 1] != '\\'))
            if (c == ';')
                return new Pair<>(DELIMITER, context);
            context += c;
            return new Pair<>(STRING_CONTENT, context);
        }
    };

    abstract Pair<CharState, String> handleChar(char[] line, int pos, String context);

    boolean isValid(Character c) {
        return Character.isLetter(c) || Character.isDigit(c) || c == '.' || c == '-' || c == '/' || c == '<' || c == '>';
    }
}
