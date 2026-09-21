<!-- conventions · v1.23.0 -->
Shared conventions of the robertblust, guestgraph and companygraph organizations live in `conventions/`, vendored from robertblust/conventions at the release `conventions.json` names. Read them before writing or committing anything here.

- `conventions/WRITING.md` — how we write: one voice, three registers, English and German.
- `conventions/WORKING.md` — how we work with git and GitHub.
- `conventions/REPOSITORIES.md` — the family: what each repository is and what pins what.
- `conventions/WRITER.md`, `conventions/TRANSLATOR.md`, `conventions/GLOSSARY.md` — the two roles that
  make a text, and the terms they keep.

Everything below this block is this repository's own. `sh conventions/conventions-sync check` says whether the copy matches the release, `sync` brings it to the release the pin names, and `sh conventions/conventions-check` holds this repository's own Markdown to `WRITING.md`, and `sh conventions/conventions-format` to its one form, which `fix` writes. Edit a shared file in robertblust/conventions, never here.
<!-- end conventions -->

# service-conventions — working conventions

The code-level rules of the guestgraph services: one list of what every service has, whatever
its stack, and one directory per stack holding the files that make it true. Every guestgraph
service vendors a stack's directory at a pinned release, the way every member of the family
vendors `conventions/`. It is specified in the engine repository, under
`specs/007-service-conventions/`, and that specification is the owner of every decision here:
this file says how to build and check, not what to build.

## Build and check

```bash
sh tests/run                    # the sync check and the service check against fixtures
sh conventions/conventions-check
```

## Checks

Two jobs, both required by the ruleset on `main`: `tests`, this repository's own, and
`conventions`, called from robertblust/conventions at the pinned tag and shown by GitHub as
`conventions / conventions`. The prose check leaves out `tests`, whose fixtures quote the very
words a service's README must carry.
