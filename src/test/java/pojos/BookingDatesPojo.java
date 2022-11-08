package pojos;






public class BookingDatesPojo {
    /*
       "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        }
     */

  //1-Tum Keyler icin private variable'lar olusturuyoruz
  private String checkin;
  private String checkout;

  //2-Tum parametrelerle ve parametresiz constructor'lar olusutruyoruz


  public BookingDatesPojo() {
  }

  public BookingDatesPojo(String checkin, String checkout) {
    this.checkin = checkin;
    this.checkout = checkout;
  }

  //3- Public getter ver setter methodlarini olusturuyoruz

  public String getCheckin() {
    return checkin;
  }

  public void setCheckin(String checkin) {
    this.checkin = checkin;
  }

  public String getCheckout() {
    return checkout;
  }

  public void setCheckout(String checkout) {
    this.checkout = checkout;
  }

  //4- toStrong() methodunu olusturuyoruz.

  @Override
  public String toString() {
    return "BookingdatesPojo{" +
            "checkin='" + checkin + '\'' +
            ", checkout='" + checkout + '\'' +
            '}';
  }


}
