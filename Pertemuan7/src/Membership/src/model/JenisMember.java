package Membership.src.model;

public class JenisMember {
    private String id;
    private String nama;

    // Default constructor
    public JenisMember() {}

    // Constructor with parameters
    public JenisMember(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    // Getters and Setters
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    // Override toString for better object representation
    @Override
    public String toString() {
        return "JenisMember{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                '}';
    }

    // Override equals and hashCode for proper object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JenisMember that = (JenisMember) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}