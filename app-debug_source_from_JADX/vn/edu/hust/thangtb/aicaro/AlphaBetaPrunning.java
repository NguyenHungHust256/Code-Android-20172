package vn.edu.hust.thangtb.aicaro;

import android.graphics.Point;
import android.support.graphics.drawable.PathInterpolatorCompat;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class AlphaBetaPrunning {
    int ai = 2;
    int[] attackScore = new int[]{0, 4, 28, 256, 2308};
    private String[] caseAI = new String[]{"0220", "02221", "0220201", "12220", "020220", "022020", "02220", "022221", "122220", "1222020", "022220", "22222", "0222021", "20202022", "0202220", "0222020", "0222201", "220220", "022022", "0202221", "22220", ";22220", "02222;"};
    private String[] caseHuman = new String[]{"0110", "01112", "0110102", "21110", "010110", "011010", "01110", "011112", "211110", "2111010", "011110", "11111", "0111012", "10101011", "0101110", "0111010", "0111102", "110110", "011011", "0101112", "11110", ";11110", "01111;"};
    int[] defenseScore = new int[]{0, 1, 9, 85, 769};
    int[][] evaluateSquare;
    int human = 1;
    private int maxDepth;
    int maxSquare;
    int f11n;
    private int[] point = new int[]{6, 4, 4, 4, 12, 12, 12, 1000, 1000, 1000, PathInterpolatorCompat.MAX_NUM_POINTS, 10000, 1000, PathInterpolatorCompat.MAX_NUM_POINTS, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000};
    Random rand;

    public AlphaBetaPrunning(int size, int maxDepth) {
        this.f11n = size;
        this.rand = new Random();
        this.evaluateSquare = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.f11n, this.f11n});
        this.maxSquare = 4;
        this.maxDepth = maxDepth;
    }

    void resetValue() {
        for (int i = 0; i < this.f11n; i++) {
            for (int j = 0; j < this.f11n; j++) {
                this.evaluateSquare[i][j] = 0;
            }
        }
    }

    public void evaluateEachSquare(CaroBoard b, int Player) {
        this.f11n = b.getSize();
        resetValue();
        int row = 0;
        while (row < this.f11n) {
            int colum = 0;
            while (colum < this.f11n - 4) {
                int i;
                int[] iArr;
                int i2;
                int countAI = 0;
                int countHuman = 0;
                for (i = 0; i < 5; i++) {
                    if (b.getSquare()[row][colum + i] == 2) {
                        countAI++;
                    }
                    if (b.getSquare()[row][colum + i] == 1) {
                        countHuman++;
                    }
                }
                if (countAI * countHuman == 0 && countAI != countHuman) {
                    i = 0;
                    while (i < 5) {
                        if (b.getSquare()[row][colum + i] == 0) {
                            if (countAI == 0) {
                                if (Player == 2) {
                                    iArr = this.evaluateSquare[row];
                                    i2 = colum + i;
                                    iArr[i2] = iArr[i2] + this.defenseScore[countHuman];
                                } else {
                                    iArr = this.evaluateSquare[row];
                                    i2 = colum + i;
                                    iArr[i2] = iArr[i2] + this.attackScore[countHuman];
                                }
                                if (CheckPoint(row, colum - 1) && CheckPoint(row, colum + 5) && b.getSquare()[row][colum - 1] == 2 && b.getSquare()[row][colum + 5] == 2) {
                                    this.evaluateSquare[row][colum + i] = 0;
                                }
                            }
                            if (countHuman == 0) {
                                if (Player == 1) {
                                    iArr = this.evaluateSquare[row];
                                    i2 = colum + i;
                                    iArr[i2] = iArr[i2] + this.defenseScore[countAI];
                                } else {
                                    iArr = this.evaluateSquare[row];
                                    i2 = colum + i;
                                    iArr[i2] = iArr[i2] + this.attackScore[countAI];
                                }
                                if (CheckPoint(row, colum - 1) && CheckPoint(row, colum + 5) && b.getSquare()[row][colum - 1] == 1 && b.getSquare()[row][colum + 5] == 1) {
                                    this.evaluateSquare[row][colum + i] = 0;
                                }
                            }
                            if ((countAI == 4 || countHuman == 4) && ((CheckPoint(row, (colum + i) - 1) && b.getSquare()[row][(colum + i) - 1] == 0) || (CheckPoint(row, (colum + i) + 1) && b.getSquare()[row][(colum + i) + 1] == 0))) {
                                iArr = this.evaluateSquare[row];
                                i2 = colum + i;
                                iArr[i2] = iArr[i2] * 2;
                            } else if (countAI == 4 || countHuman == 4) {
                                iArr = this.evaluateSquare[row];
                                i2 = colum + i;
                                iArr[i2] = iArr[i2] * 2;
                            }
                        }
                        i++;
                    }
                }
                colum++;
            }
            row++;
        }
        row = 0;
        while (row < this.f11n - 4) {
            colum = 0;
            while (colum < this.f11n) {
                countAI = 0;
                countHuman = 0;
                for (i = 0; i < 5; i++) {
                    if (b.getSquare()[row + i][colum] == 2) {
                        countAI++;
                    }
                    if (b.getSquare()[row + i][colum] == 1) {
                        countHuman++;
                    }
                }
                if (countAI * countHuman == 0 && countAI != countHuman) {
                    i = 0;
                    while (i < 5) {
                        if (b.getSquare()[row + i][colum] == 0) {
                            if (countAI == 0) {
                                if (Player == 2) {
                                    iArr = this.evaluateSquare[row + i];
                                    iArr[colum] = iArr[colum] + this.defenseScore[countHuman];
                                } else {
                                    iArr = this.evaluateSquare[row + i];
                                    iArr[colum] = iArr[colum] + this.attackScore[countHuman];
                                }
                                if (CheckPoint(row - 1, colum) && CheckPoint(row + 5, colum) && b.getSquare()[row - 1][colum] == 2 && b.getSquare()[row + 5][colum] == 2) {
                                    this.evaluateSquare[row + i][colum] = 0;
                                }
                            }
                            if (countHuman == 0) {
                                if (Player == 1) {
                                    iArr = this.evaluateSquare[row + i];
                                    iArr[colum] = iArr[colum] + this.defenseScore[countAI];
                                } else {
                                    iArr = this.evaluateSquare[row + i];
                                    iArr[colum] = iArr[colum] + this.attackScore[countAI];
                                }
                                if (CheckPoint(row - 1, colum) && CheckPoint(row + 5, colum) && b.getSquare()[row - 1][colum] == 1 && b.getSquare()[row + 5][colum] == 1) {
                                    this.evaluateSquare[row + i][colum] = 0;
                                }
                            }
                            if ((countAI == 4 || countHuman == 4) && ((CheckPoint((row + i) - 1, colum) && b.getSquare()[(row + i) - 1][colum] == 0) || (CheckPoint((row + i) + 1, colum) && b.getSquare()[(row + i) + 1][colum] == 0))) {
                                iArr = this.evaluateSquare[row + i];
                                iArr[colum] = iArr[colum] * 2;
                            } else if (countAI == 4 || countHuman == 4) {
                                iArr = this.evaluateSquare[row + i];
                                iArr[colum] = iArr[colum] * 2;
                            }
                        }
                        i++;
                    }
                }
                colum++;
            }
            row++;
        }
        row = 0;
        while (row < this.f11n - 4) {
            colum = 0;
            while (colum < this.f11n - 4) {
                countAI = 0;
                countHuman = 0;
                for (i = 0; i < 5; i++) {
                    if (b.getSquare()[row + i][colum + i] == 2) {
                        countAI++;
                    }
                    if (b.getSquare()[row + i][colum + i] == 1) {
                        countHuman++;
                    }
                }
                if (countAI * countHuman == 0 && countAI != countHuman) {
                    i = 0;
                    while (i < 5) {
                        if (b.getSquare()[row + i][colum + i] == 0) {
                            if (countAI == 0) {
                                if (Player == 2) {
                                    iArr = this.evaluateSquare[row + i];
                                    i2 = colum + i;
                                    iArr[i2] = iArr[i2] + this.defenseScore[countHuman];
                                } else {
                                    iArr = this.evaluateSquare[row + i];
                                    i2 = colum + i;
                                    iArr[i2] = iArr[i2] + this.attackScore[countHuman];
                                }
                                if (CheckPoint(row - 1, colum - 1) && CheckPoint(row + 5, colum + 5) && b.getSquare()[row - 1][colum - 1] == 2 && b.getSquare()[row + 5][colum + 5] == 2) {
                                    this.evaluateSquare[row + i][colum + i] = 0;
                                }
                            }
                            if (countHuman == 0) {
                                if (Player == 1) {
                                    iArr = this.evaluateSquare[row + i];
                                    i2 = colum + i;
                                    iArr[i2] = iArr[i2] + this.defenseScore[countAI];
                                } else {
                                    iArr = this.evaluateSquare[row + i];
                                    i2 = colum + i;
                                    iArr[i2] = iArr[i2] + this.attackScore[countAI];
                                }
                                if (CheckPoint(row - 1, colum - 1) && CheckPoint(row + 5, colum + 5) && b.getSquare()[row - 1][colum - 1] == 1 && b.getSquare()[row + 5][colum + 5] == 1) {
                                    this.evaluateSquare[row + i][colum + i] = 0;
                                }
                            }
                            if ((countAI == 4 || countHuman == 4) && ((CheckPoint((row + i) - 1, (colum + i) - 1) && b.getSquare()[(row + i) - 1][(colum + i) - 1] == 0) || (CheckPoint((row + i) + 1, (colum + i) + 1) && b.getSquare()[(row + i) + 1][(colum + i) + 1] == 0))) {
                                iArr = this.evaluateSquare[row + i];
                                i2 = colum + i;
                                iArr[i2] = iArr[i2] * 2;
                            } else if (countAI == 4 || countHuman == 4) {
                                iArr = this.evaluateSquare[row + i];
                                i2 = colum + i;
                                iArr[i2] = iArr[i2] * 2;
                            }
                        }
                        i++;
                    }
                }
                colum++;
            }
            row++;
        }
        row = 4;
        while (row < this.f11n) {
            colum = 0;
            while (colum < this.f11n - 4) {
                countAI = 0;
                countHuman = 0;
                for (i = 0; i < 5; i++) {
                    if (b.getSquare()[row - i][colum + i] == 2) {
                        countAI++;
                    }
                    if (b.getSquare()[row - i][colum + i] == 1) {
                        countHuman++;
                    }
                }
                if (countAI * countHuman == 0 && countAI != countHuman) {
                    i = 0;
                    while (i < 5) {
                        if (b.getSquare()[row - i][colum + i] == 0) {
                            if (countAI == 0) {
                                if (Player == 2) {
                                    iArr = this.evaluateSquare[row - i];
                                    i2 = colum + i;
                                    iArr[i2] = iArr[i2] + this.defenseScore[countHuman];
                                } else {
                                    iArr = this.evaluateSquare[row - i];
                                    i2 = colum + i;
                                    iArr[i2] = iArr[i2] + this.attackScore[countHuman];
                                }
                                if (CheckPoint(row + 1, colum - 1) && CheckPoint(row - 5, colum + 5) && b.getSquare()[row + 1][colum - 1] == 2 && b.getSquare()[row - 5][colum + 5] == 2) {
                                    this.evaluateSquare[row - i][colum + i] = 0;
                                }
                            }
                            if (countHuman == 0) {
                                if (Player == 1) {
                                    iArr = this.evaluateSquare[row - i];
                                    i2 = colum + i;
                                    iArr[i2] = iArr[i2] + this.defenseScore[countAI];
                                } else {
                                    iArr = this.evaluateSquare[row - i];
                                    i2 = colum + i;
                                    iArr[i2] = iArr[i2] + this.attackScore[countAI];
                                }
                                if (CheckPoint(row + 1, colum - 1) && CheckPoint(row - 5, colum + 5) && b.getSquare()[row + 1][colum - 1] == 1 && b.getSquare()[row - 5][colum + 5] == 1) {
                                    this.evaluateSquare[row + i][colum + i] = 0;
                                }
                            }
                            if ((countAI == 4 || countHuman == 4) && ((CheckPoint((row - i) + 1, (colum + i) - 1) && b.getSquare()[(row - i) + 1][(colum + i) - 1] == 0) || (CheckPoint((row - i) - 1, (colum + i) + 1) && b.getSquare()[(row - i) - 1][(colum + i) + 1] == 0))) {
                                iArr = this.evaluateSquare[row - i];
                                i2 = colum + i;
                                iArr[i2] = iArr[i2] * 2;
                            } else if (countAI == 4 || countHuman == 4) {
                                iArr = this.evaluateSquare[row - i];
                                i2 = colum + i;
                                iArr[i2] = iArr[i2] * 2;
                            }
                        }
                        i++;
                    }
                }
                colum++;
            }
            row++;
        }
    }

    public State getMaxSquare() {
        Point p = new Point(0, 0);
        ArrayList<State> list = new ArrayList();
        int t = Integer.MIN_VALUE;
        for (int i = 0; i < this.f11n; i++) {
            for (int j = 0; j < this.f11n; j++) {
                if (t < this.evaluateSquare[i][j]) {
                    t = this.evaluateSquare[i][j];
                    p.set(i, j);
                    list.clear();
                    list.add(new State(p, t));
                } else if (t == this.evaluateSquare[i][j]) {
                    p.set(i, j);
                    list.add(new State(p, t));
                }
            }
        }
        int x = this.rand.nextInt(list.size());
        this.evaluateSquare[((State) list.get(x)).getP().x][((State) list.get(x)).getP().y] = 0;
        return (State) list.get(x);
    }

    private int evaluationBoard(CaroBoard b) {
        int i;
        int j;
        String s = ";";
        for (i = 0; i < this.f11n; i++) {
            for (j = 0; j < this.f11n; j++) {
                s = s + b.getSquare()[i][j];
            }
            s = s + ";";
            for (j = 0; j < this.f11n; j++) {
                s = s + b.getSquare()[j][i];
            }
            s = s + ";";
        }
        for (i = 0; i < this.f11n - 4; i++) {
            for (j = 0; j < this.f11n - i; j++) {
                s = s + b.getSquare()[j][i + j];
            }
            s = s + ";";
        }
        for (i = this.f11n - 5; i > 0; i--) {
            for (j = 0; j < this.f11n - i; j++) {
                s = s + b.getSquare()[i + j][j];
            }
            s = s + ";";
        }
        for (i = 4; i < this.f11n; i++) {
            for (j = 0; j <= i; j++) {
                s = s + b.getSquare()[i - j][j];
            }
            s = s + ";";
        }
        for (i = this.f11n - 5; i > 0; i--) {
            for (j = this.f11n - 1; j >= i; j--) {
                s = s + b.getSquare()[j][((this.f11n + i) - j) - 1];
            }
            s = s + ";\n";
        }
        int diem = 0;
        for (i = 0; i < this.caseHuman.length; i++) {
            diem = (diem + (this.point[i] * count(s, this.caseAI[i]))) - (this.point[i] * count(s, this.caseHuman[i]));
        }
        return diem;
    }

    public int count(String s, String find) {
        int i = 0;
        while (Pattern.compile(find).matcher(s).find()) {
            i++;
        }
        return i;
    }

    public Point search(CaroBoard bb) {
        int i;
        CaroBoard b = new CaroBoard(bb.getSize());
        for (i = 0; i < this.f11n; i++) {
            for (int j = 0; j < this.f11n; j++) {
                b.getSquare()[i][j] = bb.getSquare()[i][j];
            }
        }
        evaluateEachSquare(b, 2);
        ArrayList<State> list = new ArrayList();
        for (i = 0; i < this.maxSquare; i++) {
            list.add(getMaxSquare());
        }
        int maxp = Integer.MIN_VALUE;
        ArrayList<State> ListChoose = new ArrayList();
        for (i = 0; i < list.size(); i++) {
            b.getSquare()[((State) list.get(i)).getP().x][((State) list.get(i)).getP().y] = 2;
            int t = MinVal(b, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
            if (maxp < t) {
                maxp = t;
                ListChoose.clear();
                ListChoose.add(list.get(i));
            } else if (maxp == t) {
                ListChoose.add(list.get(i));
            }
            b.getSquare()[((State) list.get(i)).getP().x][((State) list.get(i)).getP().y] = 0;
        }
        return ((State) ListChoose.get(this.rand.nextInt(ListChoose.size()))).getP();
    }

    private int MaxVal(CaroBoard b, int alpha, int beta, int depth) {
        int val = evaluationBoard(b);
        if (depth >= this.maxDepth || Math.abs(val) > PathInterpolatorCompat.MAX_NUM_POINTS) {
            return val;
        }
        int i;
        evaluateEachSquare(b, 2);
        ArrayList<State> list = new ArrayList();
        for (i = 0; i < this.maxSquare; i++) {
            list.add(getMaxSquare());
        }
        for (i = 0; i < list.size(); i++) {
            b.getSquare()[((State) list.get(i)).getP().x][((State) list.get(i)).getP().y] = 2;
            alpha = Math.max(alpha, MinVal(b, alpha, beta, depth + 1));
            b.getSquare()[((State) list.get(i)).getP().x][((State) list.get(i)).getP().y] = 0;
            if (alpha >= beta) {
                break;
            }
        }
        return alpha;
    }

    private int MinVal(CaroBoard b, int alpha, int beta, int depth) {
        int val = evaluationBoard(b);
        if (depth >= this.maxDepth || Math.abs(val) > PathInterpolatorCompat.MAX_NUM_POINTS) {
            return val;
        }
        int i;
        evaluateEachSquare(b, 1);
        ArrayList<State> list = new ArrayList();
        for (i = 0; i < this.maxSquare; i++) {
            list.add(getMaxSquare());
        }
        for (i = 0; i < list.size(); i++) {
            b.getSquare()[((State) list.get(i)).getP().x][((State) list.get(i)).getP().y] = 1;
            beta = Math.min(beta, MaxVal(b, alpha, beta, depth + 1));
            b.getSquare()[((State) list.get(i)).getP().x][((State) list.get(i)).getP().y] = 0;
            if (alpha >= beta) {
                break;
            }
        }
        return beta;
    }

    public boolean CheckPoint(int x, int y) {
        return x >= 0 && y >= 0 && x < 15 && y < 15;
    }
}
