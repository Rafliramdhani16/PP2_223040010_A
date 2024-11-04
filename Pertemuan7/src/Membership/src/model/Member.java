package Membership.src.model;

public class Member {
    private String id;
    private String nama;
    private JenisMember jenisMember;

    // Default constructor
    public Member() {}

    // Constructor with parameters
    public Member(String id, String nama, JenisMember jenisMember) {
        this.id = id;
        this.nama = nama;
        this.jenisMember = jenisMember;
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

    public void setJenisMember(JenisMember jenisMember) {
        this.jenisMember = jenisMember;
    }

    public JenisMember getJenisMember() {
        return jenisMember;
    }

    // Validate member data
    public boolean isValid() {
        return id != null && !id.trim().isEmpty() &&
                nama != null && !nama.trim().isEmpty() &&
                jenisMember != null;
    }

    // Override toString for better object representation
    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", jenisMember=" + jenisMember +
                '}';
    }

    // Override equals and hashCode for proper object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;
        return id != null ? id.equals(member.id) : member.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // Clone method for creating copies
    public Member clone() {
        Member clone = new Member();
        clone.setId(this.id);
        clone.setNama(this.nama);
        clone.setJenisMember(this.jenisMember);
        return clone;
    }
}