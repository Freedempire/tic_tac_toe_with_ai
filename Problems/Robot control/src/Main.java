class Move {
    public static void moveRobot(Robot robot, int toX, int toY) {
        int disX = distanceX(robot, toX);
        int disY = distanceY(robot, toY);
        int newDx;
        int newDy;

        if (disX > 0) {
            newDx = 1;
        } else {
            newDx = -1;
        }

        if (disY > 0) {
            newDy = 1;
        } else {
            newDy = -1;
        }

        if (disX * disY != 0) {
            if (Math.abs(newDx - robot.getDirection().dx()) <= Math.abs(newDy - robot.getDirection().dy())) {
                moveX(robot, toX, newDx);
                moveY(robot, toY, newDy);
            } else {
                moveY(robot, toY, newDy);
                moveX(robot, toX, newDx);
            }
        } else {
            if (disX != 0) {
                moveX(robot, toX, newDx);
            } else {
                moveY(robot, toY, newDy);
            }
        }
    }

    public static int distanceX(Robot robot, int toX) {
        return toX - robot.getX();
    }

    public static int distanceY(Robot robot, int toY) {
        return toY - robot.getY();
    }

    public static void moveX(Robot robot, int toX, int newDx) {
        int dx = robot.getDirection().dx();
        if (dx * newDx == -1) {
            robot.turnRight();
            robot.turnRight();
        } else if (dx * newDx == 0) {
            if (robot.getDirection().dy() * newDx == 1) {
                robot.turnRight();
            } else {
                robot.turnLeft();
            }
        }

        while (distanceX(robot, toX) != 0) {
            robot.stepForward();
        }
    }

    public static void moveY(Robot robot, int toY, int newDy) {
        int dy = robot.getDirection().dy();
        if (dy * newDy == -1) {
            robot.turnRight();
            robot.turnRight();
        } else if (dy * newDy == 0) {
            if (robot.getDirection().dx() * newDy == 1) {
                robot.turnLeft();
            } else {
                robot.turnRight();
            }
        }

        while (distanceY(robot, toY) != 0) {
            robot.stepForward();
        }
    }
}

enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                throw new IllegalStateException();
        }
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void stepForward() {
        x += direction.dx();
        y += direction.dy();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}