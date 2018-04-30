package db;

import java.util.InputMismatchException;

public class IntEntry extends Entry<Integer> {
    public IntEntry() {
        this("NOVALUE");
    }

    public IntEntry(String val) {
        nanValue = false;
        entryType = "int";
        if (val.equals("NaN")) {
            value = Integer.MAX_VALUE;
            nanValue = true;
            noValue = false;
        }
        else if (val.equals("NOVALUE")) {
            value = 0;
            noValue = true;
        } else {
            value = Integer.parseInt(val);
            noValue = false;
        }
    }

    @Override
    public Entry plus(Entry e) {
        if (nanValue || e.nanValue) {
            return new IntEntry("NaN");
        }
        else if (e.entryType.equals("int")) {
            return new IntEntry(Integer.toString(value + (Integer) e.value));
        }
        else if (e.entryType.equals("float")) {
            return new FloatEntry(Float.toString(value + (Float) e.value));
        } else {
            throw new InputMismatchException("Wrong type");
        }
    }

    @Override
    public Entry minus(Entry e) {
        if (nanValue || e.nanValue) {
            return new IntEntry("NaN");
        }
        else if (e.entryType.equals("int")) {
            return new IntEntry(Integer.toString(value - (Integer) e.value));
        }
        else if (e.entryType.equals("float")) {
            return new FloatEntry(Float.toString(value - (Float) e.value));
        } else {
            throw new InputMismatchException("Wrong type");
        }
    }

    @Override
    public Entry mul(Entry e) {
        if (nanValue || e.nanValue) {
            return new IntEntry("NaN");
        }
        else if (e.entryType.equals("int")) {
            return new IntEntry(Integer.toString(value * (Integer) e.value));
        }
        else if (e.entryType.equals("float")) {
            return new FloatEntry(Float.toString(value * (Float) e.value));
        } else {
            throw new InputMismatchException("Wrong type");
        }
    }

    @Override
    public Entry div(Entry e) {
        if (nanValue || e.nanValue) {
            return new IntEntry("NaN");
        }
        else if (e.entryType.equals("int")) {
            if ((int) e.value == 0) {
                return new IntEntry("NaN");
            }
            return new IntEntry(Integer.toString(value / (Integer) e.value));
        }
        else if (e.entryType.equals("float")) {
            if ((float) e.value == 0.0) {
                return new FloatEntry("NaN");
            }
            return new FloatEntry(Float.toString(value / (Float) e.value));
        } else {
            throw new InputMismatchException("Wrong type");
        }
    }

    @Override
    public boolean equals(Entry e) {
        if (noValue || e.noValue) {
            return false;
        }
        else if (e.entryType.equals("int")) {
            return value == (Integer) e.value;
        }
        else if (e.entryType.equals("float")) {
            return (float) value == (float) e.value;
        } else {
            throw new InputMismatchException("Wrong type");
        }
    }

    @Override
    public boolean isGreater(Entry e) {
        if (noValue || e.noValue) {
            return false;
        }
        else if (e.entryType.equals("int")) {
            return value > (Integer) e.value;
        }
        else if (e.entryType.equals("float")) {
            return (float) value > (float) e.value;
        } else {
            throw new InputMismatchException("Wrong type");
        }
    }

    @Override
    public boolean isLess(Entry e) {
        if (noValue || e.noValue) {
            return false;
        }
        else if (e.entryType.equals("int")) {
            return value < (Integer) e.value;
        }
        else if (e.entryType.equals("float")) {
            return (float) value < (float) e.value;
        } else {
            throw new InputMismatchException("Wrong type");
        }
    }

    @Override
    public boolean equals(String val) {
        if (noValue) {
            return false;
        }
        return value == Integer.parseInt(val);
    }

    @Override
    public boolean isGreater(String val) {
        if (noValue) {
            return false;
        }
        return value > Float.parseFloat(val);
    }

    @Override
    public boolean isLess(String val) {
        if (noValue) {
            return false;
        }
        return value < Float.parseFloat(val);
    }

    public IntEntry duplicate(){
        return new IntEntry(Integer.toString(value));
    }
}
