package Model;

import android.os.Parcel;
import android.os.Parcelable;

public class DataUser implements Parcelable {
    private String nama, umur, alamat;

    public DataUser(String nama, String umur, String alamat) {
        this.nama = nama;
        this.umur = umur;
        this.alamat = alamat;
    }

    protected DataUser(Parcel in) {
        nama = in.readString();
        umur = in.readString();
        alamat = in.readString();
    }

    public static final Creator<DataUser> CREATOR = new Creator<DataUser>() {
        @Override
        public DataUser createFromParcel(Parcel in) {
            return new DataUser(in);
        }

        @Override
        public DataUser[] newArray(int size) {
            return new DataUser[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(umur);
        dest.writeString(alamat);
    }
}
