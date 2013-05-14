package net.egork.misc;

/**
 * @author egorku@yandex-team.ru
 */
public abstract class MapVisitor {
    protected final int rowCount;
    protected final int columnCount;
    protected final int[] dRow;
    protected final int[] dColumn;
    protected int[] rowQueue;
    protected int[] columnQueue;
    protected int start, end;

    public MapVisitor(int rowCount, int columnCount) {
        this(rowCount, columnCount, MiscUtils.DX4, MiscUtils.DY4);
    }

    public MapVisitor(int rowCount, int columnCount, int[] dRow, int[] dColumn, int queueCapacity) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.dRow = dRow;
        this.dColumn = dColumn;
        rowQueue = new int[queueCapacity];
        columnQueue = new int[queueCapacity];
    }

    public MapVisitor(int rowCount, int columnCount, int[] dRow, int[] dColumn) {
        this(rowCount, columnCount, dRow, dColumn, rowCount * columnCount);
    }

    protected abstract void process(int row, int column, int fromRow, int fromColumn);

    public void processAll() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++)
                process(i, j);
        }
    }

    public void process(int startRow, int startColumn) {
        start = 0;
        end = 0;
        internalProcess(startRow, startColumn, -1, -1);
        while (start != end) {
            int row = rowQueue[start];
            int column = columnQueue[start++];
            if (start == rowQueue.length)
                start = 0;
            for (int i = 0; i < dRow.length; i++) {
                int nextRow = row + dRow[i];
                int nextColumn = column + dColumn[i];
                if (nextRow >= 0 && nextRow < rowCount && nextColumn >= 0 && nextColumn < columnCount)
                    internalProcess(nextRow, nextColumn, row, column);
            }
        }
    }

    protected void internalProcess(int row, int column, int fromRow, int fromColumn) {
        process(row, column, fromRow, fromColumn);
    }

    protected void add(int row, int column) {
        rowQueue[end] = row;
        columnQueue[end++] = column;
        if (end == rowQueue.length)
            end = 0;
    }

    protected void addFront(int row, int column) {
        if (--start == -1)
            start = rowQueue.length - 1;
        rowQueue[start] = row;
        columnQueue[start] = column;
    }
}
