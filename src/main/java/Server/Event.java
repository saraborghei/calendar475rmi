package Server; /**
 * Created by sarab on 4/29/2017.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Event implements Comparable<Event> {
    private String id;
    private int control;//0: private, 1: public, 2: group, 3: open
    private Date begin;
    private Date end;
    private String description;

    public Event(Date begin, Date end, String description) {
        // event are private unless specified
        this.control = 0;
        this.begin = begin;
        this.end = end;
        this.description = description;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the control
     */
    public int getControl() {
        return control;
    }

    /**
     * @param control the control to set
     */
    public void setControl(int control) {
        this.control = control;
    }

    /**
     * @return the begin
     */
    public Date getBegin() {
        return begin;
    }

    /**
     * @param begin the begin to set
     */
    public void setBegin(Date begin) {
        this.begin = begin;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * compare to events return
     *  0 if this contains arg0
     *  1 if there is no intersection between arg0 and this
     *  -1 if this intersects to arg0
     *
     * @param arg0 the event to compare
     * @return the comparison result
     */
    @Override
    public int compareTo(Event arg0) {
        int beginComp = this.getBegin().compareTo(arg0.getBegin());
        int endComp = this.getEnd().compareTo(arg0.getEnd());
        if (beginComp <= 0) {
            // this contain arg0
            if (endComp >= 0) {
                return 0;
            }
            // this intersect arg0
            else {
                return -1;
            }
        }
        // arg0 doesn't contain or intersect with this
        else if (arg0.getEnd().compareTo(this.getBegin()) <= 0) {
            return 1;
        }
        // arg0 intersect this
        else return -1;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String event = "ID: " + this.getId() + "\n"
                + "Access Control: " + this.control
                + "Begin Date: " + sdf.format(this.getBegin()) + "\n"
                + "End Date: " + sdf.format(this.getEnd()) + "\n"
                + "Description: " + this.getDescription() + "\n";
        return event;
    }

    public long timeToBegin() {
        return this.begin.getTime() - System.currentTimeMillis();
    }
}