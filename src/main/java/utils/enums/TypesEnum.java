package utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public enum TypesEnum {

    INTEGER("Integer") {
        @Override
        public String getPostgresType() {
            return "integer";
        }

        @Override
        public Integer getNumericPrecision() {
            return 32;
        }
    },
    LONG("Long") {
        @Override
        public String getPostgresType() {
            return "bigint";
        }

        @Override
        public Integer getNumericPrecision() {
            return 64;
        }
    },
    STRING("String") {
        @Override
        public String getPostgresType() {
            return "character varying";
        }

        @Override
        public Integer getNumericPrecision() {
            return 0;
        }
    },
    TIMESTAMP("Timestamp") {
        @Override
        public String getPostgresType() {
            return "timestamp without time zone";
        }

        @Override
        public Integer getNumericPrecision() {
            return 0;
        }
    },
    BOOLEAN("Boolean") {
        @Override
        public String getPostgresType() {
            return "boolean";
        }

        @Override
        public Integer getNumericPrecision() {
            return 0;
        }
    },

    FLOAT("Float") {
        @Override
        public String getPostgresType() {
            return "real";
        }

        @Override
        public Integer getNumericPrecision() {
            return 24;
        }
    },
    DOUBLE("Double") {
        @Override
        public String getPostgresType() {
            return "double precision";
        }

        @Override
        public Integer getNumericPrecision() {
            return 53;
        }
    },
    DATE("Date") {
        @Override
        public String getPostgresType() {
            return "date";
        }

        @Override
        public Integer getNumericPrecision() {
            return 0;
        }
    };


    private String javaType;

    public String getPostgresType() {
        return "";
    }

    public Integer getNumericPrecision() {
        return 0;
    }

}
