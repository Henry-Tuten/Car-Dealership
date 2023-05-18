public class StaffFactory { // factory pattern

    public static Staff GenerateStaff(FNCD.StaffType staffType){ // want to do static since we want to access creating this from anywhere
        switch(staffType){ // just based on staff type enum instead of string
            case SALESPERSON:
                return new Salesperson(); // no break statements needed since each one returns
            case MECHANIC:
                return new Mechanic();
            case DRIVER:
                return new Driver();
            default: // just default to creating intern
                return new Intern();
        }
    }
}
