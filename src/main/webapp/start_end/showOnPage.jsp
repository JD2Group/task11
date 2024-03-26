<table>
    <tr>
        <td>
            <form action="<%=action%>" method="get">
                <input type="hidden" value="3" name="countOnPage">
                <button class="<%=countOnPage==3?"btn btn-success":"btn btn-light"%>" type="submit"> show 3</button>
            </form>
        </td>
        <td>
            <form action="<%=action%>" method="get">
                <input type="hidden" value="5" name="countOnPage">
                <button class="<%=countOnPage==5?"btn btn-success":"btn btn-light"%>" type="submit"> show 5</button>
            </form>
        </td>
        <td>
            <form action="<%=action%>" method="get">
                <input type="hidden" value="10" name="countOnPage">
                <button class="<%=countOnPage==10?"btn btn-success":"btn btn-light"%>" type="submit">show 10
                </button>
            </form>
        </td>
    </tr>
</table>