package maksim.nk;


public class Airport {
    private String airportID;
    private String airportName;
    private String contactName;
    private String contactTitle;
    private String phone;
    private String fullAddress;

    public static class Builder {
        private String airportID;
        private String airportName;
        private String contactName;
        private String contactTitle;
        private String phone;
        private String fullAddress;

        public Builder withAirportID(String airportID) {
            this.airportID = airportID;
            return this;
        }

        public Builder withAirportName(String airportName) {
            this.airportName = airportName;
            return this;
        }

        public Builder withContactName(String contactName) {
            this.contactName = contactName;
            return this;
        }

        public Builder withContactTitle(String contactTitle) {
            this.contactTitle = contactTitle;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withFullAddress(String fullAddress) {
            this.fullAddress = fullAddress;
            return this;
        }

        public Airport build() {
            Airport airport = new Airport();
            airport.airportID = this.airportID;
            airport.airportName = this.airportName;
            airport.contactName = this.contactName;
            airport.contactTitle = this.contactTitle;
            airport.phone = this.phone;
            airport.fullAddress = this.fullAddress;
            return airport;
        }
    }

    public String getAirportID() {
        return airportID;
    }

    public void setAirportID(String airportID) {
        this.airportID = airportID;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @Override
    public String toString() {
        return "Airport\n" +
                "airportID='" + airportID + "\n" +
                "airportName='" + airportName + "\n" +
                "contactName='" + contactName + "\n" +
                "contactTitle='" + contactTitle + "\n" +
                "phone='" + phone + "\n" +
                "fullAddress='" + fullAddress + "\n";
    }
}

