public class Matrix {
    Rational[][] box;
    int row;
    int col;

    public Matrix(Rational[][] box){
        this.box = box;
        this.row = box.length;
        this.col = box[0].length;
    }

    public Matrix(int[][] box){
        this.row = box.length;
        this.col = box[0].length;
        this.box = new Rational[row][col];
        for(int i = 0 ; i < box.length; i++){
            for(int j = 0; j < box[0].length; j++){
                this.box[i][j] = new Rational(box[i][j]);
            }
        }
    }

    public void set(int row, int col, Rational val){
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

    public static Matrix identityMatrix(int n){
        Rational[][] box = new Rational[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) {
                    box[i][i] = Rational.ONE;
                }else{
                    box[i][j] = Rational.ZERO;
                }
            }
        }

        return new Matrix(box);
    }

    public Matrix add(Matrix B){
        if(this.row != B.row || this.col != B.col){
            return null;
        };
        Rational[][] c = new Rational[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                c[i][j] = box[i][j].add(B.box[i][j]);
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

        Rational[][] c = new Rational[newrow][newcol];
        for(int i = 0; i < newrow; i++){
            for(int j = 0; j < newcol; j++){

                Rational ij = new Rational(0);
                for(int k = 0; k < this.col; k++){
                    ij = ij.add(box[i][k].times(B.box[k][j]));
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

        Rational[][] mbox = new Rational[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col ; j++){
                mbox[i][j] = box[j][i];
            }
        }

        return new Matrix(mbox);
    }

    public Matrix copy(){
        Rational[][] mbox = new Rational[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col ; j++){
                mbox[i][j] = box[i][j];
            }
        }

        return new Matrix(mbox);
    }

    public void switchRows(int row1, int row2){
        Rational temp = new Rational();
        for(int i = 0; i < col; i++){
            temp = box[row1][i];
            box[row1][i] = box[row2][i];
            box[row2][i] = temp;
        }
    }

    public void addMultipleRowsTo(Rational multiple, int srcRow, int targetRow){
        for(int i = 0; i < col; i++){
            box[targetRow][i] = box[targetRow][i].add(multiple.times(box[srcRow][i]));
        }
    }



    public Rational det(){
        Rational mul = Rational.ONE;
        if(row != col){
            return null;
        }else{
            Matrix B = this.copy();
            Rational[][] box = B.box;
                for(int r = 0; r < row; r++) {

                        int i = r + 1;
                        if (i < row && box[r][r].equals(Rational.ZERO)) {

                            while (i < row) {
                                if (!box[i][r].equals(Rational.ZERO)) {
                                    B.switchRows(r, i);
                                    mul = mul.inverse();
                                    break;
                                }
                                i++;
                            }
                        }





                        if(box[r][r].equals(Rational.ZERO)){
                            return Rational.ZERO;
                        }


                        Rational divisor = new Rational(box[r][r].numerator,box[r][r].denominator);
                        mul = mul.times(divisor);
                        for (int j = r; j < col; j++) {
                            box[r][j] = box[r][j].divide(divisor);
                        }



                        for(int j = 0; j < row; j++){
                            if(j != r){
                                B.addMultipleRowsTo(box[j][r].inverse(), r, j);
                            }
                        }

//                        System.out.println(this.toString());
//                        System.out.println(mul);

                }


            for(int i = 0; i < row; i++){
                mul = mul.times(box[i][i]);
            }


            return mul;
        }

    }







    public Matrix inverse() {
        if(row != col){
            return null;
        }else{
            Matrix res = Matrix.identityMatrix(row);

            Matrix B = this.copy();
            Rational[][] box = B.box;
            for(int r = 0; r < row; r++) {

                int i = r + 1;
                if (i < row && box[r][r].equals(Rational.ZERO)) {

                    while (i < row) {
                        if (!box[i][r].equals(Rational.ZERO)) {
                            B.switchRows(r, i);
                            res.switchRows(r,i);
                            break;
                        }
                        i++;
                    }
                }





                if(box[r][r].equals(Rational.ZERO)){
                    return null;
                }



                Rational divisor = new Rational(box[r][r].numerator,box[r][r].denominator);
                Rational resDivisor = new Rational(res.box[r][r].numerator,res.box[r][r].denominator);
                for (int j = 0; j < col; j++) {
                    box[r][j] = box[r][j].divide(divisor);
                    res.box[r][j] = res.box[r][j].divide(divisor);
                }



//                System.out.println(B);
//                System.out.println(res + "\n---------------------------+++++------------");

                for(int j = 0; j < row; j++){
                    if(j != r){
                        Rational multi = new Rational(box[j][r].numerator,box[j][r].denominator);
                        B.addMultipleRowsTo(multi.inverse(), r, j);
                        res.addMultipleRowsTo(multi.inverse(),r,j);
//                        System.out.println("第"+(r+1)+"行 乘" + multi.inverse() + " 加到第"+(j+1)+"行");
//                        System.out.println(B);
//                        System.out.println(res + "\n---------------------------+++++------------");
                    }
                }





//                        System.out.println(this.toString());
//                        System.out.println(mul);

            }
            return res;
        }

//        return res;
    }




    public static void main(String[] args){

        int[][] a = {
                {1,1,0},
                {2,7,5},
                {6,4,9},
};

        Matrix A = new Matrix(a);
        System.out.println(A.inverse());
        System.out.println(A.det());
//        System.out.println(A);
//        Matrix B = A.transpose();
//        System.out.println(A);
//        System.out.println(B);

//        Rational r1 = new Rational(5,4);
//        Rational r2 = new Rational(3,7);
//        System.out.println(r1.divide(r2));


    }


}
