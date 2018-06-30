package vn.edu.hust.thangtb.aicaro;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Array;

public class MainActivity extends Activity {
    public static final int TABLE_HEIGHT = 15;
    public static final int TABLE_WIDTH = 15;
    public static boolean activeGame = true;
    public static int f12m;
    public static int f13n;
    public static int f14r;
    public static int turn = 0;
    private ImageButton[][] Cells = ((ImageButton[][]) Array.newInstance(ImageButton.class, new int[]{15, 15}));
    private int[][] MAXTRIX = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{15, 15}));
    private AlphaBetaPrunning ai;
    private CaroBoard caro;
    private int[] dataImage = new int[2];
    private int first = 0;
    private int maxDepth = 3;
    private Button newG;
    private int shape = 3;
    private TableLayout table;
    private TextView txtTurn;

    class C03141 implements OnClickListener {
        C03141() {
        }

        public void onClick(View view) {
            MainActivity.this.newGame();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0317R.layout.activity_game);
        this.txtTurn = (TextView) findViewById(C0317R.id.txtTurn);
        this.ai = new AlphaBetaPrunning(15, this.maxDepth);
        this.newG = (Button) findViewById(C0317R.id.btnNewGame);
        Setup();
        loadResource();
        this.caro = new CaroBoard(15);
        desginBoardGame();
        this.newG.setOnClickListener(new C03141());
        if (turn == 1) {
            this.txtTurn.setText("Your Turn");
        } else {
            this.txtTurn.setText("Ai 's thinking .....");
        }
    }

    public void newGame() {
        activeGame = true;
        turn = this.first;
        if (turn == 1) {
            this.txtTurn.setText("Your Turn");
        } else {
            this.txtTurn.setText("Ai 's thinking .....");
        }
        for (int i1 = 0; i1 < 15; i1++) {
            for (int j1 = 0; j1 < 15; j1++) {
                this.Cells[i1][j1].setClickable(true);
                this.Cells[i1][j1].setBackgroundResource(C0317R.drawable.shape_cell);
                this.MAXTRIX[i1][j1] = 0;
                this.caro.set(i1, j1, 0);
                final int i = i1;
                final int j = j1;
                this.Cells[i][j].setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (MainActivity.turn == 1 && MainActivity.activeGame) {
                            MainActivity.this.Cells[i][j].setBackgroundResource(MainActivity.this.dataImage[MainActivity.turn]);
                            MainActivity.this.Cells[i][j].setClickable(false);
                            MainActivity.f12m = i;
                            MainActivity.f13n = j;
                            MainActivity.f14r = 1;
                            MainActivity.this.MAXTRIX[i][j] = 1;
                            MainActivity.this.caro.set(i, j, 1);
                            MainActivity.turn = 0;
                            if (MainActivity.this.checkWin(MainActivity.this.MAXTRIX, MainActivity.f12m, MainActivity.f13n, MainActivity.f14r)) {
                                MainActivity.activeGame = false;
                                MainActivity.this.txtTurn.setText("You are Winner");
                            } else if (MainActivity.turn == 0 && MainActivity.activeGame && MainActivity.this.MAXTRIX[i][j] == 1) {
                                MainActivity.this.BotPlay();
                            }
                        }
                    }
                });
            }
        }
        if (this.first == 0 && activeGame) {
            BotPlay(7, 7);
        }
    }

    private void BotPlay() {
        make_a_move();
    }

    private void BotPlay(int x, int y) {
        make_a_move(x, y);
    }

    private void make_a_move() {
        new AsyncTaskGame(this.txtTurn, this.Cells, this.caro, this.dataImage, this.ai, this.MAXTRIX, this).execute(new String[0]);
    }

    private void make_a_move(int x, int y) {
        this.Cells[x][y].setBackgroundResource(this.dataImage[turn]);
        this.Cells[x][y].setClickable(false);
        f12m = x;
        f13n = y;
        f14r = 2;
        this.MAXTRIX[x][y] = 2;
        this.caro.set(x, y, 2);
        turn = 1;
    }

    public void loadResource() {
        if (this.shape == 3) {
            this.dataImage[1] = C0317R.drawable.cross;
            this.dataImage[0] = C0317R.drawable.nought;
        } else if (this.shape == 4) {
            this.dataImage[0] = C0317R.drawable.cross;
            this.dataImage[1] = C0317R.drawable.nought;
        }
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

    public void desginBoardGame() {
        activeGame = true;
        this.table = (TableLayout) findViewById(C0317R.id.GameBoard);
        for (int y = 0; y < 15; y++) {
            int row = y;
            TableRow r1 = new TableRow(this);
            for (int x = 0; x < 15; x++) {
                int col = x;
                this.Cells[y][x] = new ImageButton(this);
                this.Cells[y][x].setBackgroundResource(C0317R.drawable.shape_cell);
                final int finalY = y;
                final int finalX = x;
                this.Cells[y][x].setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (MainActivity.turn == 1 && MainActivity.activeGame) {
                            Log.d("Load Image ", "Chuan bi");
                            MainActivity.this.Cells[finalY][finalX].setBackgroundResource(MainActivity.this.dataImage[MainActivity.turn]);
                            Log.d("Load Image ", "Xong");
                            MainActivity.this.Cells[finalY][finalX].setClickable(false);
                            MainActivity.this.MAXTRIX[finalY][finalX] = 1;
                            MainActivity.this.caro.set(finalY, finalX, 1);
                            MainActivity.f12m = finalY;
                            MainActivity.f13n = finalX;
                            MainActivity.f14r = 1;
                            MainActivity.turn = 0;
                            if (MainActivity.this.checkWin(MainActivity.this.MAXTRIX, MainActivity.f12m, MainActivity.f13n, MainActivity.f14r)) {
                                MainActivity.activeGame = false;
                                MainActivity.this.txtTurn.setText("You are Winner");
                                Toast.makeText(MainActivity.this, "Winner", 0).show();
                            } else if (MainActivity.turn == 0 && MainActivity.activeGame && MainActivity.this.MAXTRIX[finalY][finalX] == 1) {
                                MainActivity.this.BotPlay();
                            }
                        }
                    }
                });
                r1.addView(this.Cells[y][x], 100, 100);
            }
            this.table.addView(r1, 1500, 100);
        }
        if (this.first == 0 && activeGame) {
            BotPlay(7, 7);
        }
    }

    public void Setup() {
        Bundle bundle = getIntent().getBundleExtra("SetUp");
        this.shape = Integer.parseInt(bundle.getString("shape"));
        int parseInt = Integer.parseInt(bundle.getString("fistPlayer"));
        this.first = parseInt;
        turn = parseInt;
        this.maxDepth = Integer.parseInt(bundle.getString("level"));
    }
}
