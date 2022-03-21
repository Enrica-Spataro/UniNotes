public class Recensione {

    private String nickName;
    private int voto;

    public Recensione(String nickName,int voto){
        this.nickName=nickName;
        this.voto=voto;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
}
