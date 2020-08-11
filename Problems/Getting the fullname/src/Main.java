class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        if (firstName != null)
            this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName != null)
            this.lastName = lastName;
    }

    public String getFullName() {
        String fullName;
        if (firstName.equals("")) {
            if (lastName.equals(""))
                fullName = "Unknown";
            else
                fullName = lastName;
        } else {
            if (lastName.equals(""))
                fullName = firstName;
            else
                fullName = firstName + " " + lastName;
        }

        return fullName;
    }
}