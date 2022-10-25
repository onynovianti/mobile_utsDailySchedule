package polinema.ac.id.utsony.models;

public class SuperHero {
    public String no;
    public String tugas;
    public String deskripsi;
    public String target;
    public String prioritas;
    private boolean isChecked;

    public SuperHero(String No, String tugas, String deskripsi, String target, String prioritas){
        this.tugas = tugas;
        this.deskripsi = deskripsi;
        this.target = target;
        this.prioritas = prioritas;
    }

    public String getTugas() {
        return tugas;
    }

    public void setTugas(String tugas) {
        this.tugas = tugas;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public boolean getChecked() {
        return isChecked;
    }
    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
