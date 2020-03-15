package interfaces;

import issues.Issue;

public interface Filter {
    Issue find(String issueID);
}
