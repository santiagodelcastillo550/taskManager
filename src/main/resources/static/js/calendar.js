document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'es',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        eventColor: '#ff9f89',

        // Carga profesional de eventos desde el endpoint
        events: function(fetchInfo, successCallback, failureCallback) {
            fetch('/api/tasks')
                .then(response => response.json())
                .then(data => {
                    const events = data.map(task => ({
                        title: task.title,
                        start: task.start,
                        id: task.id
                    }));
                    successCallback(events); // FullCalendar añade automáticamente los eventos
                })
                .catch(error => {
                    console.error('Error al cargar tareas:', error);
                    failureCallback(error);
                });
        }
    });

    calendar.render();
});
