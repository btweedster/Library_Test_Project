package src;

public class Library {
    DataConnect dc = new DataConnect();

    public void addPatron(String name) {
        dc.addPatron(new Patron(name));
    }

    public boolean removePatron(String id) {
        return dc.removePatron(id);
    }

    public boolean addMedia(String title, char type) {
        Media m = null;
        switch (type) {
            case 'B' : 
                m = new Book(title);
                break;
            case 'M' :
                m = new Music(title);
                break;
            case 'F' :
                m = new Film(title);
                break;
        }
        if (m == null) {
            return false;
        }
        dc.addMedia(m);
        return true;
    }

    public boolean addMedia(String title, String secParam, char type) {
        Media m = null;
        switch (type) {
            case 'B' : 
                m = new Book(title,secParam);
                break;
            case 'M' :
                m = new Music(title,secParam);
                break;
            case 'F' :
                m = new Film(title,Integer.valueOf(secParam));
                break;
        }
        if (m == null) {
            return false;
        }
        dc.addMedia(m);
        return true;
    }

    public boolean removeMedia(String id) {
        return dc.removeMedia(id);
    }

    public void updateMedia(String id) {

    }

    public boolean checkout(String pID, String mID) {
        Patron p = dc.getPatron(pID);
        Media m = dc.getMedia(mID);
        if (p != null && m != null) {
            return dc.getMedia(mID).checkout(dc.getPatron(pID));
        }
        return false;
    }

    public boolean checkin(String mID) {
        Media m = dc.getMedia(mID);
        if (m != null) {
            return dc.getMedia(mID).checkin();
        }
        return false;
    }
}
