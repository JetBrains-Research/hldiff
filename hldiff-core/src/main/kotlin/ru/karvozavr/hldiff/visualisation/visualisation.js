/*$.ready(() => {
    let $changed = $('span');

    $changed.each(() => {
        alert($(this))
    });

    $changed.each(function () {
        // create an annotation for each marker
        const top = this.offsetTop - 20, left = this.offsetLeft,
            style = 'style="top:' + top + 'px;left:' + left + 'px;"';
        alert(top);
        $(this).parent().append('<div class="anno" ' + style + '>' + 'Annotated' + '</div>');
    });

    $changed.on('mouseover', function () {
        console.log(this.offsetLeft, this.offsetTop);
    });
});*/

function drawLine(start, end, line) {
    const x1 = start.offsetLeft;
    const y1 = start.offsetTop;
    const x2 = end.offsetLeft;
    const y2 = end.offsetTop;

    line.setAttribute('x1', x1);
    line.setAttribute('y1', y1);
    line.setAttribute('x2', x2);
    line.setAttribute('y2', y2);
}


const result = document.getElementsByClassName('changed-code');
console.log(result.length);
const line = document.getElementById('line');

for (let elem of result) {
    elem.addEventListener('mouseenter', function () {
        let changeId = elem.className.split(' ').find((v) => v.startsWith('code-change-'));

        const start = elem;
        const end = document.getElementById(changeId);

        console.log(start);

        // TODO draw line
    });

    console.log(elem.offsetTop)
}

console.log('HELLOOOOOOO  ! THERE');