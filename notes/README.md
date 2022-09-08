# Team notes

This repository holds all planning documents for the software design alongside any notes for all members in our team to keep in mind throughout the software development process.

## How to use `git` with `gradle`

1. `clone` the repository onto your computer. You should only have 2 files in this folder: `app/build.gradle` and `app/src`.
2. `cd` into the `app` directory.
3. Run `gradle run`. This will automatically produce all necessary gradle files as the build script is present.
4. Make changes as necessary.
5. Only add the source files and the build script to the staging area: `git add app/build.gradle app/src`
6. Run `git commit -m "<Your message here>"`.  Message should start with a present tense verb and be capitalised.
7. Run `git fetch` before pushing to ensure no one else has pushed any new code to the repo in the time you've made changes.
8. Merge conflicts using `git merge`. 
9. Run `git push`.


## Writing good commit messages
_Summarised from [How to Write a Git Commit Message](https://cbea.ms/git-commit/)_
- You can write more detailed commit messages using the command `git commit -m <title> -m <description>`. However, most commits should be short and concise (see [this guy's](https://github.com/tpope/vim-pathogen/commits/master) commit history for inspiration). General rule of thumb is to limit subject lines and/or single-line commits to 50 characters.
- Capitalise the subject line.
- Do not end the subject line with a full stop.
- Use the "imperative mood" in the subject line (written as if you're giving a command). e.g. `Merge branch 'myfeature'`, `Remove deprecated methods`, `Update getting started documentation` and NOT `Changing behavior of Player.move() method`.

## Writing and testing the code: 
- When writing code, for each method it is the responsibility of the person wrtiting the method to also write the tests as well for that specific method.
- The person is to conduct both validation and defective testing covering as many cases as possible. 

## Use case diagrams
Created using [draw.io](https://app.diagrams.net).

Please upload the `.drawio` file to the site if you wish to make any changes. Save as `.drawio` and `.pdf` and push to the repo.
