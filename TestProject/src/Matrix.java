public class Matrix {
    int[][] box;
    int row;
    int col;

    public Matrix(int[][] box){
        this.box = box;
        this.row = box.length;
        this.col = box[0].length;
    }

    public void set(int row, int col, int val){
        box[row-1][col-1] = val;
    }

    public int longestDigit(){
        int length = 0;
        for(int i = 0; i < box.length; i++){
            for(int j = 0; j < box[0].length; j++){
                String str = box[i][j]+"";
                int len = str.length();
                if(len > length){
                    length = len;
                }
            }
        }
        return length;
    }

    @Override
    public String toString(){
        String res = "|";
        int compactLength = longestDigit();

        for(int i = 0; i < box.length; i++){
            res += " ";
            for(int j = 0; j < box[0].length; j++){
                String str = box[i][j]+"";
                int coverLength = compactLength - str.length();
                String cover = "";
                for(int k = 0; k < coverLength; k++){
                    cover += " ";
                }
                str = cover + str;
                res = res + str;
                if(j < box[0].length - 1){
                    res += " ";
                }else{
                    res += " ";
                }
            }
            res += "|\n|";
        }



        return res.substring(0,res.length()-1);

    }

    public Matrix add(Matrix B){
        if(this.row != B.row || this.col != B.col){
            return null;
        };
        int[][] c = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                c[i][j] = box[i][j] + B.box[i][j];
            }
        }
        return new Matrix(c);
    }

    public Matrix times(Matrix B){
        if(this.col != B.row){
            return null;
        }

        int newrow = row;
        int newcol = B.col;

        int[][] c = new int[newrow][newcol];
        for(int i = 0; i < newrow; i++){
            for(int j = 0; j < newcol; j++){

                int ij = 0;
                for(int k = 0; k < this.col; k++){
                    ij += box[i][k] * B.box[k][j];
                }
                c[i][j] = ij;
            }
        }

        return new Matrix(c);
    }

    public Matrix transpose(){
        if(row != col){
            return null;
        }

        int[][] mbox = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col ; j++){
                mbox[i][j] = box[j][i];
            }
        }

        return new Matrix(mbox);
    }

    public static void main(String[] args){
        int[][] a = {
                {1,2,3,4},
                {5,6,7,8},
                {1,2,0,0},
                {1,0,1,0}};

        Matrix A = new Matrix(a);
        Matrix B = A.transpose();
        System.out.println(A.times(B).times(A));
        System.out.println(A.times(B.times(A)));



    }


}
