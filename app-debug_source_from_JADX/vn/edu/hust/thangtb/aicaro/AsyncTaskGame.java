package vn.edu.hust.thangtb.aicaro;

import android.app.Activity;
import android.graphics.Point;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskGame extends AsyncTask<String, String, Point> {
    private ImageButton[][] Cells;
    private Activity acc;
    private AlphaBetaPrunning ai;
    private CaroBoard caroBoard;
    private int[] data;
    private int[][] matrix;
    private TextView txt;

    public AsyncTaskGame(TextView txt, ImageButton[][] cells, CaroBoard caroBoard, int[] data, AlphaBetaPrunning ai, int[][] matrix, Activity acc) {
        this.txt = txt;
        this.Cells = cells;
        this.caroBoard = caroBoard;
        this.data = data;
        this.ai = ai;
        this.matrix = matrix;
        this.acc = acc;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        this.txt.setText("Ai is thinking .....");
    }

    protected Point doInBackground(String... strings) {
        return this.ai.search(this.caroBoard);
    }

    protected void onPostExecute(Point p) {
        super.onPostExecute(p);
        this.Cells[p.x][p.y].setBackgroundResource(this.data[0]);
        this.Cells[p.x][p.y].setClickable(false);
        this.caroBoard.set(p.x, p.y, 2);
        this.matrix[p.x][p.y] = 2;
        MainActivity.f12m = p.x;
        MainActivity.f13n = p.y;
        MainActivity.turn = 1;
        MainActivity.f14r = 2;
        Log.d("m", MainActivity.f12m + "");
        Log.d("n", MainActivity.f13n + "");
        if (checkWin(this.matrix, p.x, p.y, 2)) {
            this.txt.setText("You are loser");
            MainActivity.activeGame = false;
            Toast.makeText(this.acc.getApplicationContext(), "Loser", 0).show();
            return;
        }
        this.txt.setText("Your Turn");
    }

    private boolean checkWin(int[][] matrix, int x, int y, int c) {
        if (checkHangNgang(matrix, x, y, c) || checkHangDoc(matrix, x, y, c) || checkCheoPhai(matrix, x, y, c) || checkCheoTrai(matrix, x, y, c)) {
            return true;
        }
        return false;
    }

    private boolean checkCheoPhai(int[][] matrix, int x, int y, int c) {
        int i = -4;
        while (i <= 0) {
            int dem = 0;
            int j = i;
            while (j < i + 5) {
                if (x + j >= 0 && x + j < 15 && y - j >= 0 && y - j < 15 && matrix[x + j][y - j] == c) {
                    dem++;
                }
                j++;
            }
            if (dem == 5 && ((x + i) - 1 < 0 || (x + i) - 1 >= 15 || (y - i) + 1 < 0 || (y - i) + 1 >= 15 || matrix[(x + i) - 1][(y - i) + 1] == 0 || matrix[(x + i) - 1][(y - i) + 1] == c || (x + i) + 5 < 0 || (x + i) + 5 >= 15 || (y - i) - 5 < 0 || (y - i) - 5 >= 15 || matrix[(x + i) + 5][(y - i) - 5] == 0 || matrix[(x + i) + 5][(y - i) - 5] == c)) {
                return true;
            }
            i++;
        }
        return false;
    }

    private boolean checkCheoTrai(int[][] matrix, int x, int y, int c) {
        int i = -4;
        while (i <= 0) {
            int dem = 0;
            int j = i;
            while (j < i + 5) {
                if (x + j >= 0 && x + j < 15 && y + j >= 0 && y + j < 15 && matrix[x + j][y + j] == c) {
                    dem++;
                }
                j++;
            }
            if (dem == 5 && ((x + i) - 1 < 0 || (x + i) - 1 >= 15 || (y + i) - 1 < 0 || (y + i) - 1 >= 15 || matrix[(x + i) - 1][(y + i) - 1] == 0 || matrix[(x + i) - 1][(y + i) - 1] == c || (x + i) + 5 < 0 || (x + i) + 5 >= 15 || (y + i) + 5 < 0 || (y + i) + 5 >= 15 || matrix[(x + i) + 5][(y + i) + 5] == 0 || matrix[(x + i) + 5][(y + i) + 5] == c)) {
                return true;
            }
            i++;
        }
        return false;
    }

    private boolean checkHangDoc(int[][] matrix, int x, int y, int c) {
        int k = x - 4;
        while (k <= x) {
            if (k >= 0) {
                int dem = 0;
                int j = k;
                while (j < k + 5) {
                    if (j < 15 && matrix[j][y] == c) {
                        dem++;
                    }
                    j++;
                }
                if (dem == 5 && (k - 1 < 0 || k + 5 > 15 || matrix[k - 1][y] == 0 || matrix[k - 1][y] == c || matrix[k + 5][y] == 0 || matrix[k + 5][y] == c)) {
                    return true;
                }
            }
            k++;
        }
        return false;
    }

    private boolean checkHangNgang(int[][] matrix, int x, int y, int c) {
        int k = y - 4;
        while (k <= y) {
            if (k >= 0) {
                int dem = 0;
                int j = k;
                while (j < k + 5) {
                    if (j < 15 && matrix[x][j] == c) {
                        dem++;
                    }
                    j++;
                }
                if (dem == 5 && (k - 1 < 0 || k + 5 > 15 || matrix[x][k - 1] == 0 || matrix[x][k - 1] == c || matrix[x][k + 5] == 0 || matrix[x][k + 5] == c)) {
                    return true;
                }
            }
            k++;
        }
        return false;
    }
}
