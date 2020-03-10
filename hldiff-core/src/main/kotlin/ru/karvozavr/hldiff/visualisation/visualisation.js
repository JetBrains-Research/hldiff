function drawLine(start, end, line, color = 'rgb(0, 0, 0)') {
    let x1 = start.offsetLeft;
    let y1 = start.offsetTop + start.getBoundingClientRect().height / 2;
    let x2 = end.offsetLeft;
    let y2 = end.offsetTop + end.clientHeight / 2;

    if (x1 < x2) {
        x1 += start.getBoundingClientRect().width;
    } else {
        x2 += end.clientWidth;
    }

    document.documentElement.style.setProperty('--line-color', setAlpha(color, 1));
    line.setAttribute('x1', x1);
    line.setAttribute('y1', y1);
    line.setAttribute('x2', x2);
    line.setAttribute('y2', y2);
}

function setChangedCodeListeners(changedCode, line) {
    for (let elem of changedCode) {
        elem.addEventListener('mouseenter', function () {
            let changeId = elem.className.split(' ').find((v) => v.startsWith('code-change-'));

            const start = elem;
            const end = document.getElementById(changeId);

            console.log(start);
            drawLine(start, end, line);
        });
        console.log(elem.offsetTop)
    }
}

function setAlpha(rgba, alpha) {
    console.log(rgba);
    if (rgba.startsWith('rgba')) {
        return rgba.replace(/ ([^ ]*)\)/, alpha);
    } else {
        return rgba.replace('rgb', 'rgba').replace(/(\))/, ', ' + alpha + ')');
    }
}

function setChangesListListeners(changesList, line) {
    for (let elem of changesList) {
        let codeChanges = document.getElementsByClassName(elem.id);

        elem.addEventListener('mouseenter', function () {
            for (let change of codeChanges) {
                console.log("Enter", change.style.backgroundColor);
                change.style.backgroundColor = setAlpha(document.defaultView.getComputedStyle(change, null)['background-color'], 1);
            }

            if (elem.className.includes('change-addition')) {
                drawLine(codeChanges[0], elem, line, 'var(--add-color)')
            } else if (elem.className.includes('change-move')) {
                drawLine(codeChanges[0], elem, line, 'var(--move-color)')
            } else if (elem.className.includes('change-delete')) {
                drawLine(codeChanges[0], elem, line, 'var(--delete-color)')
            } else if (elem.className.includes('change-update')) {
                drawLine(codeChanges[0], elem, line, 'var(--update-color)')
            }
        });

        elem.addEventListener('mouseout', function () {
            for (let change of codeChanges) {
                console.log("Exit", change.style.backgroundColor);
                change.style.backgroundColor = setAlpha(document.defaultView.getComputedStyle(change, null)['background-color'], 0.3);
            }
        });
    }
}


const changedCode = document.getElementsByClassName('changed-code');
const changesList = document.getElementsByClassName('change');
console.log(changedCode.length);

const line = document.getElementById('line');


setChangedCodeListeners(changedCode, line);
setChangesListListeners(changesList, line);