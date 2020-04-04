# hldiff 
![Gradle Package](https://github.com/karvozavr/hldiff/workflows/Gradle%20Package/badge.svg)

Language-independent high-level differencing tool.

## Usage

- Download the release archive. 
- Run `./hldiff --help` for the CLI description.
- `./hldiff fileBefore.py fileAfter.py` will print JSON high-level differcence to `stdout`.
- `./hldiff --html fileBefore.py fileAfter.py` will print HTML visualisation to `stdout`.
- `./hldiff --html --batch --outdir=otput_directory` will read file path pairs from `stdin` 
  (one pair per line, space separated) and output an HTML file with diff visualisation for every given file pair to `output_directory`.


