package utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

        @Override
        public Integer getCastedData(String data) {

            return Integer.valueOf(data);
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

        @Override
        public Long getCastedData(String data) {
            return Long.valueOf(data);
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

        @Override
        public String getCastedData(String data) {
            return data;
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

        @Override
        public Timestamp getCastedData(String data) {
            //yyyy-mm-dd hh:mm:ss[.fffffffff]
            return Timestamp.valueOf(LocalDateTime
                    .parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"))
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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

        @Override
        public Boolean getCastedData(String data) {
            return Boolean.valueOf(data);
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

        @Override
        public Float getCastedData(String data) {
            return Float.valueOf(data);
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

        @Override
        public Double getCastedData(String data) {
            return Double.valueOf(data);
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

        @Override
        public Date getCastedData(String data) {
            return Date.valueOf(data);
        }
    };


    private String javaType;

    public String getPostgresType() {
        return "";
    }

    public Integer getNumericPrecision() {
        return 0;
    }

    public Object getCastedData(String data) {
        return 0;
    }
}
