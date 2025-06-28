#include <iostream>
#include <vector>
#include <queue>
#include <set>
#include <algorithm>

using namespace std;

const int ROWS = 4;
const int COLS = 3;

int dx[] = {1, 2, 2, 1, -1, -2, -2, -1};
int dy[] = {2, 1, -1, -2, -2, -1, 1, 2};

struct Move {
    int fromX, fromY;
    int toX, toY;
};

struct Board {
    vector<vector<char>> grid;
    vector<Move> moves;
    int g;

    Board() {
        grid = vector<vector<char>>(ROWS, vector<char>(COLS, '.'));
        g = 0;
    }

    bool operator<(const Board& other) const {
        return grid < other.grid;
    }
};

int heuristic(Board& b) {
    int wrong = 0;
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            if (b.grid[i][j] == 'W' && i != 0) wrong++;
            if (b.grid[i][j] == 'B' && i != 3) wrong++;
        }
    }
    return wrong;
}

bool inside(int x, int y) {
    return x >= 0 && x < ROWS && y >= 0 && y < COLS;
}

bool isGoal(Board& b) {
    return b.grid[0][0] == 'W' && b.grid[0][1] == 'W' && b.grid[0][2] == 'W' &&
           b.grid[3][0] == 'B' && b.grid[3][1] == 'B' && b.grid[3][2] == 'B';
}

void printBoard(Board& b) {
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            cout << b.grid[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
}

void solve(Board start) {
    auto cmp = [](pair<int, Board> a, pair<int, Board> b) {
        return a.first > b.first;
    };

    priority_queue<pair<int, Board>, vector<pair<int, Board>>, decltype(cmp)> pq(cmp);
    set<vector<vector<char>>> visited;

    pq.push({heuristic(start), start});

    Board best;
    int minSteps = 1e9;

    while (!pq.empty()) {
        Board cur = pq.top().second;
        pq.pop();

        if (visited.count(cur.grid)) continue;
        visited.insert(cur.grid);

        if (isGoal(cur)) {
            if (cur.g < minSteps) {
                minSteps = cur.g;
                best = cur;
            }
            continue;
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (cur.grid[i][j] == '.') continue;

                for (int d = 0; d < 8; d++) {
                    int ni = i + dx[d];
                    int nj = j + dy[d];

                    if (inside(ni, nj) && cur.grid[ni][nj] == '.') {
                        Board next = cur;
                        swap(next.grid[i][j], next.grid[ni][nj]);
                        next.g = cur.g + 1;
                        next.moves.push_back({i, j, ni, nj});
                        int cost = next.g + heuristic(next);
                        pq.push({cost, next});
                    }
                }
            }
        }
    }

    cout << "Best path with " << best.moves.size() << " moves:\n";
    Board temp = start;
    printBoard(temp);

    for (auto m : best.moves) {
        cout << "Move from (" << m.fromX << "," << m.fromY << ") to (" << m.toX << "," << m.toY << ")\n";
        swap(temp.grid[m.fromX][m.fromY], temp.grid[m.toX][m.toY]);
        printBoard(temp);
        cout << "-----\n";
    }

    cout << "Total moves: " << best.moves.size() << endl;
}

int main() {
    Board start;
    start.grid[0][0] = 'B';
    start.grid[0][1] = 'B';
    start.grid[0][2] = 'B';
    start.grid[3][0] = 'W';
    start.grid[3][1] = 'W';
    start.grid[3][2] = 'W';

    solve(start);
}
