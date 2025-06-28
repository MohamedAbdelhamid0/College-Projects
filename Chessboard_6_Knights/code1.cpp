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
    
    Board() {
        grid = vector<vector<char>>(ROWS, vector<char>(COLS, '.'));
    }
    
    bool operator<(const Board& other) const {
        return grid < other.grid;
    }
};

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
    queue<Board> q;
    set<vector<vector<char>>> visited;
    
    q.push(start);
    visited.insert(start.grid);
    
    while (!q.empty()) {
        Board cur = q.front();
        q.pop();
        
        if (isGoal(cur)) {
            cout << "Solution found with " << cur.moves.size() << " moves:\n";
            Board temp = start;
            printBoard(temp);
            
            for (auto m : cur.moves) {
                cout << "Move from (" << m.fromX << "," << m.fromY << ") to (" << m.toX << "," << m.toY << ")\n";
                swap(temp.grid[m.fromX][m.fromY], temp.grid[m.toX][m.toY]);
                printBoard(temp);
                cout << "-----\n";
            }
            
            cout << "Total moves: " << cur.moves.size() << endl;
            return;
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
                        
                        if (!visited.count(next.grid)) {
                            next.moves.push_back({i, j, ni, nj});
                            visited.insert(next.grid);
                            q.push(next);
                        }
                    }
                }
            }
        }
    }
    
    cout << "No solution found!" << endl;
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
    
    return 0;
}