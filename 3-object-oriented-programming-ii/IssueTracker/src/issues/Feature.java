package issues;

import enums.IssuePriority;
import enums.IssueResolution;
import enums.IssueStatus;
import enums.WorkAction;

public class Feature extends Issue {
    public Feature(IssuePriority priority, Component component, String description) {
        super(priority, component, description);
    }

    @Override
    public void resolve(IssueResolution resolution) {
        boolean isDesigned = false;
        boolean isImplemented = false;
        boolean isTested = false;
        if (this.getCapacityOfLog() != 0) {
            for (int i = 0; i < this.getCapacityOfLog(); i++) {
                if (this.getActionLog()[i].contains(WorkAction.DESIGN.name().toLowerCase())) {
                    isDesigned = true;
                } else if (this.getActionLog()[i].contains(WorkAction.IMPLEMENTATION.name().toLowerCase())) {
                    isImplemented = true;
                } else if (this.getActionLog()[i].contains(WorkAction.TESTS.name().toLowerCase())) {
                    isTested = true;
                }
            }
        }
        if (resolution == IssueResolution.FIXED) {
            if (!(isDesigned && isImplemented && isTested)) {
                throw new RuntimeException("Bug can't be resolved without being fixed and tested");
            } else {
                this.setResolution(resolution);
                this.setStatus(IssueStatus.RESOLVED);
            }
        } else {
            this.setResolution(resolution);
        }
    }

    @Override
    public boolean validateAction(WorkAction action) {
        return true;
    }
}
