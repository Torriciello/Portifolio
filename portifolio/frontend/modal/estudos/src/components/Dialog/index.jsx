import React, { useRef } from "react";

export function Dialog() {
  const dialogRef = useRef(null);

  const openDialog = () => {
    dialogRef.current.showModal();
  };

  const closeDialog = () => {
    dialogRef.current.close();
  };

  return (
    <>
      <dialog ref={dialogRef}>
        <button onClick={closeDialog} autoFocus>
          Close
        </button>
        <p>This modal dialog has a groovy backdrop!</p>
      </dialog>

      <button onClick={openDialog}>
        Show the dialog
      </button>
    </>
  );
}