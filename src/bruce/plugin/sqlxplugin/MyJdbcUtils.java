package bruce.plugin.sqlxplugin;

import com.intellij.openapi.util.text.StringUtil;

import java.sql.Types;

public class MyJdbcUtils {
    public static int convertFromJdbcTypeName(String jdbcTypeName, int size, String databaseType) {
        //support for all other types.
        if (StringUtil.isEmpty(jdbcTypeName)) {
            return 1111;
        } else {
            String fixed = jdbcTypeName.toUpperCase();
            if (fixed.contains("BIGINT")) {
                return Types.BIGINT;
            }
            if (fixed.contains("TINYINT")) {
                if (size == 1) {
                    return Types.BOOLEAN;
                }
                return Types.TINYINT;
            }

            if (fixed.contains("LONGVARBINARY")) {
                return Types.LONGVARBINARY;
            }

            if (fixed.contains("VARBINARY")) {
                return Types.VARBINARY;
            }

            if (fixed.contains("LONGVARCHAR")) {
                return Types.LONGVARCHAR;
            }

            if (fixed.contains("SMALLINT")) {
                return Types.SMALLINT;
            }

            if (fixed.contains("DATETIME")) {
                return Types.TIMESTAMP;
            }

            if (fixed.equals("DATE") && databaseType.equals(DataBaseConstants.ORACLE)) {
                return Types.TIMESTAMP;
            }

            if (fixed.contains("NUMBER")) {
                return Types.DECIMAL;
            }

            if (fixed.contains("BOOLEAN")) {
                return Types.BOOLEAN;
            }

            if(fixed.endsWith("TEXT")){
                return Types.LONGVARCHAR;
            }

            if(fixed.equals("BINARY_DOUBLE")){
                return Types.DOUBLE;
            }

            if(fixed.equals("BINARY_FLOAT")){
                return Types.FLOAT;
            }

            if (fixed.contains("BINARY")) {
                return -3;
            } else if (fixed.contains("BIT")) {
                return -7;
            } else if (fixed.contains("BOOL")) {
                return 16;
            } else if (fixed.contains("DATE")) {
                return 91;
            } else if (fixed.contains("TIMESTAMP")) {
                return 93;
            } else if (fixed.contains("TIME")) {
                return 92;
            } else if (!fixed.contains("REAL") && !fixed.contains("NUMBER")) {
                if (fixed.contains("FLOAT")) {
                    return 6;
                } else if (fixed.contains("DOUBLE")) {
                    return 8;
                } else if (fixed.equals("CHAR") && !fixed.contains("VAR")) {
                    return 1;
                } else if (fixed.contains("INT") && !fixed.contains("INTERVAL")) {
                    return 4;
                } else if (fixed.contains("DECIMAL")) {
                    return 3;
                } else if (fixed.contains("NUMERIC")) {
                    return 2;
                } else if (!fixed.contains("CHAR") && !fixed.contains("TEXT")) {
                    if (fixed.contains("BLOB")) {
                        return 2004;
                    } else if (fixed.contains("CLOB")) {
                        return 2005;
                    } else {
                        return fixed.contains("REFERENCE") ? 2006 : 1111;
                    }
                } else {
                    return 12;
                }
            } else {
                return 7;
            }
        }
    }
}
