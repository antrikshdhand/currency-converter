# SOFT2412 Assignment 1

**CC_06_Thu_14_Rasim_Group-2**

Here's our shared repo for SOFT2412 Assignment 1.

Contributors:

```bash
| name           | unikey   | sid       |
|----------------|----------|-----------|
| Antriksh Dhand | adha5655 | 510415022 |
| Nemo Gage      | ngag3129 | 500496851 |
| Udit Samant    | usam6049 | 500700976 |
| Sulav Malla    | smal8154 | 500495980 |
```


## How to use `git` with `gradle`

1. `clone` the repository onto your computer. You should only have 2 files in this folder: `app/build.gradle` and `app/src`.
2. Run `gradle run`. This will automatically produce all necessary gradle files as the build script is present.
3. Make changes as necessary.
4. Only add the source files and the build script to the staging area: `git add app/build.gradle app/src`
5. Run `git commit -m "<Your message here>"`
6. Run `git fetch` before pushing to ensure no one else has pushed any new code to the repo in the time you've made changes.
7. Merge conflicts using `git merge`. 
8. Run `git push`.

## Writing good commit messages
_Summarised from [How to Write a Git Commit Message](https://cbea.ms/git-commit/)_
- You can write more detailed commit messages using the command `git commit -m <title> -m <description>`. However, most commits should be short and concise (see [this guy's](https://github.com/tpope/vim-pathogen/commits/master) commit history for inspiration). General rule of thumb is to limit subject lines and/or single-line commits to 50 characters.
- Capitalise the subject line.
- Do not end the subject line with a full stop.
- Use the "imperative mood" in the subject line (written as if you're giving a command). e.g. `Merge branch 'myfeature'`, `Remove deprecated methods`, `Update getting started documentation` and NOT `Changing behavior of Player.move() method`.
