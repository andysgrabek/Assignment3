package main.java.com.andys.grabek;

public class CheckersBoardPrinter {
    
    CheckersBoard board;

    CheckersBoardPrinter(CheckersBoard board) {
        this.board = board;
    }
    
    public String getAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < board.getBoardSize() + 4; ++j) {
            for (int i = 0; i < board.getBoardSize() + 3; ++i) {
                if (isBoardCornerPadding(j, i)) {
                    stringBuilder.append(" ");
                }
                else if (isVerticalBoardDescription(j, i)) {
                    stringBuilder.append(j - 2);
                }
                else if (isHorizontalBoardDescription(j, i)) {
                    stringBuilder.append(i - 2).append(" ");
                }
                else if (isHorizontalBoardEdge(j, i)) {
                    stringBuilder.append("--");
                }
                else if (isBoardCorner(j, i)) {
                    stringBuilder.append("+");
                }
                else if (isVerticalBoardEdge(j, i)) {
                    stringBuilder.append("|");
                }
                else if (isBoardContent(j, i)) {
                    stringBuilder.append(board.getPlayerOnField(i - 2, j - 2)).append(" ");
                }
                else {
                    stringBuilder.append("  ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Checks if the coordinates for board printing provided are part of the invisible board padding.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the invisible board padding
     */
    private boolean isBoardCornerPadding(int j, int i) {
        return i == 0 && j == 0 || i == 0 && j == 1 || i == 1 && j == 0 || i == 0 && j == board.getBoardSize() + 3 ||
                i == 0 && j == board.getBoardSize() + 2 || i == 1 && j == board.getBoardSize() + 3 ||
                i == board.getBoardSize() + 2 && j == 0 || i == board.getBoardSize() + 2 && j == board.getBoardSize() + 3;
    }

    /**
     * Checks if the coordinates for board printing provided are part of the vertical board description.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the vertical board description
     */
    private boolean isVerticalBoardDescription(int j, int i) {
        return i == 0 && j > 1 && j < board.getBoardSize() + 2;
    }

    /**
     * Checks if the coordinates for board printing provided are part of the horizontal board description.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the horizontal board description
     */
    private boolean isHorizontalBoardDescription(int j, int i) {
        return (j == 0 || j == board.getBoardSize() + 3) && i > 1 && i < board.getBoardSize() + 2;
    }

    /**
     * Checks if the coordinates for board printing provided are part of the board content.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the board content
     */
    private boolean isBoardContent(int j, int i) {
        return i > 1 && j > 1 && j < board.getBoardSize() + 2 && board.getPlayerOnField(i - 2, j - 2) == 1 ||
                board.getPlayerOnField(i - 2, j - 2) == 2;
    }

    /**
     * Checks if the coordinates for board printing provided are part of the vertical board edge.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the vertical board edge
     */
    private boolean isVerticalBoardEdge(int j, int i) {
        return (i == 1 || i == board.getBoardSize() + 2) && j > 1 && j < board.getBoardSize() + 2;
    }

    /**
     * Checks if the coordinates for board printing provided are part of the horizontal board edge.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the horizontal board edge
     */
    private boolean isHorizontalBoardEdge(int j, int i) {
        return i > 1 && i < board.getBoardSize() + 2 && (j == 1 || j == board.getBoardSize() + 2);
    }

    /**
     * Checks if the coordinates for board printing provided are part of the board border corner.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the board border corner
     */
    private boolean isBoardCorner(int j, int i) {
        return i == 1 && j == 1
                || i == board.getBoardSize() + 2 && j == board.getBoardSize() + 2
                || i == 1 && j == board.getBoardSize() + 2
                || i == board.getBoardSize() + 2 && j == 1;
    }
    
}
