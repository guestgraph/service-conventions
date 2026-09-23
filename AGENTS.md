<!-- conventions · v1.28.0 -->
Shared conventions of the robertblust, guestgraph and companygraph organizations live in `conventions/`, vendored from robertblust/conventions at the release `conventions.json` names. Read them before writing or committing anything here.

- `conventions/WRITING.md` — how we write: one voice, three registers, English and German.
- `conventions/WORKING.md` — how we work with git and GitHub.
- `conventions/REPOSITORIES.md` — the family: what each repository is and what pins what.
- `conventions/WRITER.md`, `conventions/TRANSLATOR.md`, `conventions/GLOSSARY.md` — the two roles that
  make a text, and the terms they keep.

Everything below this block is this repository's own. `sh conventions/conventions-sync check` says whether the copy matches the release, `sync` brings it to the release the pin names, and `sh conventions/conventions-check` holds this repository's own Markdown to `WRITING.md`, and `sh conventions/conventions-format` to its one form, which `fix` writes. Edit a shared file in robertblust/conventions, never here.
<!-- end conventions -->

# service-conventions — working conventions

The code-level rules of the guestgraph services: one list of what every service has, whatever its stack, and one directory per stack holding the files that make it true. Every guestgraph service vendors a stack's directory at a pinned release, the way every member of the family vendors `conventions/`. It is specified in the engine repository, under `specs/007-service-conventions/`, and that specification is the owner of every decision here: this file says how to build and check, not what to build.

## Build and check

```bash
sh tests/run                    # the sync check and the service check against fixtures
sh conventions/conventions-check
```

## Releasing

The version is in three places and all of them move before the tag: the marker on the first line of `spring/AGENTS.md`, `<version>` on `io.guestgraph:service-parent` in `spring/pom.xml`, and the parent `<version>` the runtime module names in `spring/runtime/pom.xml`. `grep -rn '<the old version>' --include='*.xml' --include='*.md' spring/` finds all three and is the check worth running before the tag. The marker is what `service-conventions-sync check` compares in a service's AGENTS.md block; the pom version is what a service's own `pom.xml` must name as its parent, which `service-conventions-check` reads and Maven resolves by relativePath. Moving one and not the other leaves both sync checks green in the service and fails the job, which is how v0.10.0 went out unusable.

## Checks

Two jobs, both required by the ruleset on `main`: `tests`, this repository's own, and `conventions`, called from robertblust/conventions at the pinned tag and shown by GitHub as `conventions / conventions`. The prose check leaves out `tests`, whose fixtures quote the very words a service's README must carry.
