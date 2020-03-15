package issues;

import enums.IssuePriority;
import enums.IssueResolution;
import enums.IssueStatus;
import enums.WorkAction;

import java.time.LocalDateTime;

public abstract class Issue {
    private static final int MAX_CAPACITY_LOG = 20;
    private static int countOfIssues = 0;
    private String issueID;
    private String description;
    private IssuePriority priority;
    private IssueResolution resolution;
    private IssueStatus status;
    private Component component;
    private String[] actions;
    private int capacityOfLog;
    private LocalDateTime createdOn;
    private LocalDateTime lastModifiedOn;

    public Issue(IssuePriority priority, Component component, String description) {
        this.priority = priority;
        this.component = component;
        this.description = description;
        this.issueID = component.getShortName() + "-" + countOfIssues++;
        this.resolution = IssueResolution.UNRESOLVED;
        this.status = IssueStatus.OPEN;
        this.createdOn = LocalDateTime.now();
        this.lastModifiedOn = LocalDateTime.now();
        this.actions = new String[MAX_CAPACITY_LOG];
        this.capacityOfLog = 0;
    }

    public String getIssueID() {
        return this.issueID;
    }

    public String getDescription() {
        return this.description;
    }

    public IssuePriority getPriority() {
        return this.priority;
    }

    public IssueResolution getResolution() {
        return this.resolution;
    }

    public void setResolution(IssueResolution resolution) {
        this.resolution = resolution;
        this.lastModifiedOn = LocalDateTime.now();
    }


    public IssueStatus getStatus() {
        return this.status;
    }

    public Component getComponent() {
        return this.component;
    }

    public LocalDateTime getCreatedOn() {
        return this.createdOn;
    }

    public LocalDateTime getLastModifiedOn() {
        return this.lastModifiedOn;
    }

    public String[] getActionLog() {
        if (capacityOfLog != 0) {
            return actions;
        }
        return null;
    }

    public int getCapacityOfLog() {
        return capacityOfLog;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
        this.lastModifiedOn = LocalDateTime.now();
    }

    public void addAction(WorkAction action, String description) {
        if (description == null || description == "") {
            throw new RuntimeException("Description is mandatory.");
        } else if (this.capacityOfLog == MAX_CAPACITY_LOG) {
            throw new RuntimeException("Max capacity of logs reached.");
        } else if (!this.validateAction(action)) {
            throw new RuntimeException("Tasks can't be fixed, implemented or tested");
        } else {
            if (this.capacityOfLog == 0) {
                this.status = IssueStatus.IN_PROGRESS;
            }
            this.actions[capacityOfLog++] = action.name().toLowerCase() + ": " + description;
            this.lastModifiedOn = LocalDateTime.now();
        }
    }

    public abstract void resolve(IssueResolution resolution);

    public abstract boolean validateAction(WorkAction action);

}
