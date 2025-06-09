# GitHub Actions CI Pipeline

This repository uses GitHub Actions for continuous integration. The pipeline performs the following tasks:

## Workflow Structure

The CI pipeline (`ci.yml`) is divided into multiple jobs that run in sequence:

### 1. Build and Test Job
- Sets up Java 11 environment
- Caches Gradle dependencies for faster builds
- Builds the project
- Runs all unit tests
- Uploads test results and build artifacts

### 2. Security Scanning Jobs (Run Last)
As requested, security scans run after build and test to prioritize time-sensitive operations:

#### OWASP Dependency Check
- Scans project dependencies for known vulnerabilities
- Checks for outdated dependencies with security issues
- Generates HTML and JSON reports

#### CodeQL Analysis
- Performs static code analysis for security vulnerabilities
- Scans for common security issues in Java code
- Results are uploaded to GitHub Security tab

#### SpotBugs Scan
- Additional static analysis for bug patterns
- Focuses on security-related code issues
- Generates detailed reports

## Triggering the Workflow

The workflow automatically runs on:
- Push to main, master, or develop branches
- Pull requests targeting these branches
- Manual trigger via GitHub Actions UI (workflow_dispatch)

## Requirements

- Java 11 compatible code
- Gradle build system
- Test configuration in `src/test`

## Viewing Results

- **Test Results**: Available in the Actions tab under "Artifacts"
- **Security Scan Results**: 
  - OWASP reports in "dependency-check-report" artifact
  - CodeQL results in the Security tab
  - SpotBugs reports in "spotbugs-results" artifact

## Customization

To modify the workflow:
1. Edit `.github/workflows/ci.yml`
2. Adjust Java version if needed
3. Add or remove security scanning tools
4. Modify branch triggers as needed

## Gradle Wrapper

If your project doesn't have a Gradle wrapper (`gradlew`), the workflow will fall back to using the `gradle` command. To generate a wrapper:
```bash
gradle wrapper
```