# List ../lib/* first to prevent students from overriding JUnit
CLASSPATH="../lib/*:."

# Bash colors
RED="\e[31m"
GREEN="\e[92m"
STOP="\e[0m"

# Fail function
fail() {
    printf "${RED}"
    figlet FAILED!
    printf "${STOP}"
    >&2 echo [Fatal AutoGrader error]: $1
    exit 1
}

rm -rf student-submission

git clone $1 student-submission
if [[ $? -ne 0 ]]; then fail "Failed to clone git repository"; fi

# copy tests into submission
cp GradeListExamples.java student-submission/

cd student-submission

if [[ ! -e ListExamples.java ]]; then fail "Expected file ListExamples.java does not exist"; fi

javac -cp $CLASSPATH *.java
if [[ $? -ne 0 ]]; then fail "javac failed to compile submitted java program"; fi

java -cp $CLASSPATH org.junit.runner.JUnitCore GradeListExamples
if [[ $? -ne 0 ]]; then fail "One or more JUnit tests failed. Check the output above for more details."; fi

printf "${GREEN}"
figlet PASSED!
printf "${STOP}"