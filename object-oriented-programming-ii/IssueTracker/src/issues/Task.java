package issues;

import enums.IssuePriority;
import enums.IssueResolution;
import enums.IssueStatus;
import enums.WorkAction;

public class Task extends Issue {

    public Task(IssuePriority priority, Component component, String description) {
        super(priority, component, description);
    }

    public boolean validateAction(WorkAction action) {
        return !(action == WorkAction.FIX || action == WorkAction.IMPLEMENTATION || action == WorkAction.TESTS);
    }

    @Override
    public void resolve(IssueResolution resolution) {
        this.setResolution(resolution);
        if (resolution == IssueResolution.FIXED) {
            this.setStatus(IssueStatus.RESOLVED);
        }
    }
}
