

public class Acquisto {

    private String codiceAppunto;
    private String nickName;

    public Acquisto(String codiceAppunto, String nickName){

        this.codiceAppunto= codiceAppunto;
        this.nickName=nickName;

    }

    public String getCodiceAppunto() {
        return codiceAppunto;
    }

    public void setCodiceAppunto(String codiceAppunto) {
        this.codiceAppunto = codiceAppunto;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


}
