import enums.IssueResolution;
import enums.WorkAction;
import interfaces.Filter;
import interfaces.Repository;
import issues.Issue;

public class Jira implements Filter, Repository {
    private static final int MAX_CAPACITY = 100;
    private Issue[] issues;
    private int capacityOfIssues;

    public Jira() {
        this.issues = new Issue[MAX_CAPACITY];
        this.capacityOfIssues = 0;
    }

    public void addActionToIssue(Issue issue, WorkAction action, String actionDescription) {
        int indexOfIssue = getIndexOfIssue(issue);
        if (indexOfIssue == -1) {
            throw new RuntimeException("Issue not found!");
        }
        this.issues[indexOfIssue].addAction(action, actionDescription);
    }

    public void resolveIssue(Issue issue, IssueResolution resolution) {
        int indexOfIssue = getIndexOfIssue(issue);
        if (indexOfIssue == -1) {
            throw new RuntimeException("Issue not found!");
        }
        this.issues[indexOfIssue].resolve(resolution);
    }


    @Override
    public Issue find(String issueID) {
        if (issueID == null || this.capacityOfIssues == 0) {
            return null;
        }
        for (int i = 0; i < capacityOfIssues; i++) {
            if (this.issues[i].getIssueID().equals(issueID)) {
                return this.issues[i];
            }
        }
        return null;
    }

    @Override
    public void addIssue(Issue issue) {
        if (this.capacityOfIssues == MAX_CAPACITY) {
            throw new RuntimeException("Max capacity of issues reached.");
        } else if(getIndexOfIssue(issue) != -1) {
            throw new RuntimeException("Issue already exists");
        } else {
            this.issues[this.capacityOfIssues++] = issue;
        }
    }

    private int getIndexOfIssue(Issue issue) {
        for (int i = 0; i < capacityOfIssues; i++) {
            if (this.issues[i].getIssueID().equals(issue.getIssueID())) {
                return i;
            }
        }
        return -1;
    }

}
