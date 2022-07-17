import {GameObject} from "./GameObject";
import {Wall} from "@/assets/scripts/Wall";

export class GameMap extends GameObject {
    constructor(ctx, parent) {
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.L = 0; //一个单位的长度

        this.rows = 13;
        this.cols = 13;

        this.inner_walls_count = 15;
        this.walls = [];
    }

    start() {
        for (let i = 0; i < 1000; i++) {
            if (this.create_walls())
                break;
        }
    }

    check_connectivity(g, sx, sy, tx, ty) {
        if (sx === tx && sy === ty) return true;
        g[sx][sy] = true;

        let dx = [-1, 0, 1, 0], dy = [0, 1, 0, -1];
        for (let i = 0; i < 4; i++) {
            let x = sx + dx[i], y = sy + dy[i];
            if (!g[x][y] && this.check_connectivity(g, x, y, tx, ty))
                return true;
        }
    }

    create_walls() {
        const g = []; //创建一个bool数组，判断当前位置是否有墙
        //初始化
        for (let r = 0; r < this.rows; r++) {
            g[r] = [];
            for (let c = 0; c < this.cols; c++) {
                g[r][c] = false;
            }
        }
        // 给四周加上障碍物
        for (let r = 0; r < this.rows; r++) {
            //(r, 0)和(r, this.cols - 1)分别表示每行最左与最右
            g[r][0] = g[r][this.cols - 1] = true;
        }
        for (let c = 0; c < this.cols; c++) {
            //(0, c)和(this.rows - 1, c)分别表示每行最上与最下
            g[0][c] = g[this.rows - 1][c] = true;
        }
        //创建随机障碍物
        for (let i = 0; i < this.inner_walls_count; i++) {
            for (let j = 0; j < 1000; j++) {
                let r = parseInt(Math.random() * this.rows);
                let c = parseInt(Math.random() * this.cols);
                if (g[r][c] || g[c][r]) continue;
                if (r === this.rows - 2 && c === 1 || c === this.cols - 2 && 1)
                    continue;
                g[r][c] = g[c][r] = true;
                break;
            }
        }
        //深层拷贝
        const copy_g = JSON.parse(JSON.stringify(g));
        //得到连通路
        if (!this.check_connectivity(copy_g, this.rows - 2, 1, 1, this.cols - 2))
            return false;

        for (let r = 0; r < this.rows; r++) {
            for (let c = 0; c < this.cols; c++) {
                if (g[r][c]) {
                    this.walls.push(new Wall(r, c, this));
                }
            }
        }

        return true;
    }

    update_size() {
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows));
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }

    update() {
        this.update_size();
        this.render();
    }

    render() {
        const odd_color = "#aad751", even_color = "#a2d149";
        for (let r = 0; r < this.rows; r++) {
            for (let c = 0; c < this.cols; c++) {
                if ((r + c) % 2 === 0) {
                    this.ctx.fillStyle = odd_color;
                } else {
                    this.ctx.fillStyle = even_color;
                }
                this.ctx.fillRect(c * this.L, r * this.L, this.L, this.L);
            }
        }
    }
}
