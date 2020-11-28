package popor.com.vos.shoplist;

public class ListlevelVo {
    private final int readLevel;
    private final int writeLevel;

    public ListlevelVo(int readLevel, int writeLevel) {
        this.readLevel = readLevel;
        this.writeLevel = writeLevel;
    }

    public int getReadLevel() {
        return readLevel;
    }

    public int getWriteLevel() {
        return writeLevel;
    }
}

