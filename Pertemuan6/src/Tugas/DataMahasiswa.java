package Tugas;

public class DataMahasiswa {
    private String nama;
    private String nim;
    private String tanggalLahir;
    private String jenisKelamin;
    private String status;
    private String pendidikan;
    private String alamat;
    private String telepon;
    private String email;
    private String jurusan;
    private String fakultas;
    private String fasilitas;
    private String hobi;
    private int semester;
    private double ipk;
    private String catatan;

    public DataMahasiswa(String nama, String nim, String tanggalLahir, String jenisKelamin,
                         String status, String pendidikan, String alamat, String telepon,
                         String email, String jurusan, String fakultas, String fasilitas,
                         String hobi, int semester, double ipk, String catatan) {
        this.nama = nama;
        this.nim = nim;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.status = status;
        this.pendidikan = pendidikan;
        this.alamat = alamat;
        this.telepon = telepon;
        this.email = email;
        this.jurusan = jurusan;
        this.fakultas = fakultas;
        this.fasilitas = fasilitas;
        this.hobi = hobi;
        this.semester = semester;
        this.ipk = ipk;
        this.catatan = catatan;
    }

    public String getNama() { return nama; }
    public String getNim() { return nim; }
    public String getTanggalLahir() { return tanggalLahir; }
    public String getJenisKelamin() { return jenisKelamin; }
    public String getStatus() { return status; }
    public String getPendidikan() { return pendidikan; }
    public String getAlamat() { return alamat; }
    public String getTelepon() { return telepon; }
    public String getEmail() { return email; }
    public String getJurusan() { return jurusan; }
    public String getFakultas() { return fakultas; }
    public String getFasilitas() { return fasilitas; }
    public String getHobi() { return hobi; }
    public int getSemester() { return semester; }
    public double getIpk() { return ipk; }
    public String getCatatan() { return catatan; }
}
